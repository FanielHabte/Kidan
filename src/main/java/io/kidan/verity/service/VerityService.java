package io.kidan.verity.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.kidan.guardian.assembler.EntityMapper;
import io.kidan.guardian.dto.csv.response.CsvContractRule;
import io.kidan.guardian.entity.ContractRule;
import io.kidan.guardian.entity.Dataset;
import io.kidan.guardian.enums.DataType;
import io.kidan.guardian.service.ContractRuleService;
import io.kidan.guardian.service.ContractService;
import io.kidan.verity.dto.ValidationResult;
import io.kidan.verity.dto.ValidationResultFactory;
import io.kidan.verity.validations.csv.CsvDateRuleValidation;
import io.kidan.verity.validations.csv.CsvFileValidation;
import io.kidan.verity.validations.csv.CsvNumericRuleValidation;
import io.kidan.verity.validations.csv.CsvStringRuleValidation;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@Service
public class VerityService {

    private final ContractRuleService contractRuleService;
    private final ContractService contractService;
    private final EntityMapper entityMapper;
    private final ValidationResultFactory validationResultFactory;

    VerityService(ContractRuleService contractRuleService, ContractService contractService, EntityMapper entityMapper, ValidationResultFactory validationResultFactory) {
        this.contractRuleService = contractRuleService;
        this.contractService = contractService;
        this.entityMapper = entityMapper;
        this.validationResultFactory = validationResultFactory;
    }

    // Build base table and iterate through all the validations one by one
    // Return the results to process and validation
    public List<ValidationResult> validateSubmission(String filePath, Dataset dataset) throws SQLException, CsvValidationException, IOException {
        Map<String, Object> columnValidationDetails = validateColumns(filePath, dataset);
        boolean columnsAreValid = (boolean) columnValidationDetails.get("status");

        if (columnsAreValid) {
            return validateColumnRules(filePath, dataset);
        } else {
            throw new FileUploadException(String.format(
                    "Column mismatch in uploaded file. Expected columns: %s. Provided columns: %s.",
                    columnValidationDetails.get("expected").toString(),
                    columnValidationDetails.get("provided").toString()
            ));
        }

    }

    public List<ValidationResult> validateColumnRules(String filePath, Dataset dataset) throws SQLException {
        String inMemoryDb = "jdbc:duckdb:";
        List<ValidationResult> validationResults = new ArrayList<>();
        List<String> sqlQueries = fetchAllValidationQueries(filePath, dataset);
        try (Connection connection = DriverManager.getConnection(inMemoryDb);
             Statement statement = connection.createStatement()) {

            for (String sqlQuery : sqlQueries) {
                ResultSet results = statement.executeQuery(sqlQuery);

                if (results.next()) {
                    int invalidRows = results.getInt("invalidRows");
                    if (invalidRows == 0) {
                        validationResults.add(
                                validationResultFactory.buildPassedValidationResult(results.getString("columnName"))
                        );
                    } else {
                        validationResults.add(
                                validationResultFactory.buildFailedValidationResult(
                                        results.getString("columnName"),
                                        invalidRows,
                                        results.getString("validationType")
                                )
                        );
                    }

                }

            }

        } catch (SQLException e) {
            throw new SQLException("Validation query execution failed for dataset: " + dataset.getId(), e);
        }

        return validationResults;
    }

    // Fetch all contract rules based on Dataset ID
    public List<CsvContractRule> fetchAllCsvContractRules(Dataset dataset) {
        String contractId = contractService
                .findContractByDatasetId(dataset.getId())
                .getContractId();
        List<ContractRule> contractRules = contractRuleService.findContractRulesByContractId(contractId);

        return contractRules.stream()
                .map(entityMapper::buildCsvContractRule)
                .toList();
    }

    // Build a list of query to run validation
    public List<String> fetchAllValidationQueries(String filePath, Dataset dataset) {
        List<CsvContractRule> csvContractRules = fetchAllCsvContractRules(dataset);
        List<String> sqlQueries = new ArrayList<>();

        for (CsvContractRule csvContractRule : csvContractRules) {
            CsvFileValidation validation = switch (csvContractRule.getDataType()) {
                case DataType.STRING -> new CsvStringRuleValidation();
                case DataType.DATE -> new CsvDateRuleValidation();
                case DataType.INT -> new CsvNumericRuleValidation();
                default -> throw new IllegalArgumentException(
                        "Unsupported data type: " + csvContractRule.getDataType()
                );
            };
            csvContractRule.setCsvFileValidation(validation);
            String validationQuery = csvContractRule.performCsvFileValidation();

            if (validationQuery == null || validationQuery.isBlank()) {
                continue;  // skip columns with no validation rule to apply
            }

            String columnName = csvContractRule.getColumnName();
            String sqlQuery = """
                    SELECT
                        COUNT(*)      as invalidRows
                        , '%s'        as columnName
                        , '%s'        as validationType
                    FROM read_csv('%s')
                    WHERE %s
                    """.formatted(columnName, csvContractRule.getRuleType(), filePath, validationQuery);

            sqlQueries.add(sqlQuery);
        }
        return sqlQueries;
    }

    // Fetches all columns from the input file
    public List<String> fetchFileColumns(String filePath) throws IOException, CsvValidationException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        CSVReader csvReader = new CSVReader(bufferedReader);

        return Arrays.stream(csvReader.readNext())
                .map(String::trim)
                .map(s -> s.replace("\uFEFF", ""))  // ← strip BOM
                .map(String::toLowerCase)
                .sorted()
                .toList();
    }

    // Fetches all valid columns
    public List<String> fetchValidColumns(Dataset dataset) {

        return fetchAllCsvContractRules(dataset).stream()
                .map(CsvContractRule::getColumnName)
                .map(String::trim)
                .map(String::toLowerCase)
                .sorted()
                .toList();
    }

    // Compares the input file columns and valid columns to return
    public Map<String, Object> validateColumns(String filePath, Dataset dataset) throws CsvValidationException, IOException {
        Map<String, Object> columnValidationDetails  = new HashMap<>();
        columnValidationDetails.put("status", fetchFileColumns(filePath).equals(fetchValidColumns(dataset)));
        columnValidationDetails.put("expected", fetchFileColumns(filePath));
        columnValidationDetails.put("provided", fetchValidColumns(dataset));

        return columnValidationDetails;
    }


}

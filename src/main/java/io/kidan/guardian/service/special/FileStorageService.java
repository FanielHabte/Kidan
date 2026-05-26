package io.kidan.guardian.service.special;

import io.kidan.guardian.assembler.EntityMapper;
import io.kidan.guardian.dto.csv.request.CsvFormWrapper;
import io.kidan.guardian.entity.Contract;
import io.kidan.guardian.entity.ContractRule;
import io.kidan.guardian.entity.Dataset;
import io.kidan.guardian.service.ContractRuleService;
import io.kidan.guardian.service.ContractService;
import io.kidan.guardian.service.DatasetService;
import io.kidan.guardian.validation.CsvValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileStorageService {
    private final CsvValidator csvValidator;
    private final EntityMapper entityMapper;
    private final ContractService contractService;
    private final ContractRuleService contractRuleService;
    private final DatasetService datasetService;

    FileStorageService(CsvValidator csvValidator, EntityMapper entityMapper
                    , ContractService contractService, ContractRuleService contractRuleService
                    , DatasetService datasetService) {
        this.datasetService = datasetService;
        this.csvValidator = csvValidator;
        this.entityMapper = entityMapper;
        this.contractService = contractService;
        this.contractRuleService = contractRuleService;
    }

    public void saveCsvFile(Dataset dataset, CsvFormWrapper wrapper) {
        boolean isValid = csvValidator.fileValidation(wrapper);
        Contract contract = entityMapper.buildContract(wrapper.getCsvFileStructure());
        List<ContractRule> contractRuleList = entityMapper.getContractRuleList(wrapper.getCsvRuleFormList(), contract);

        if (isValid) {
            datasetService.saveDataset(dataset);
            contractService.saveContract(contract, dataset);
            contractRuleService.saveAllContractRules(contractRuleList);
        } else {
            throw new RuntimeException("Error occurred");
        }
    }

}

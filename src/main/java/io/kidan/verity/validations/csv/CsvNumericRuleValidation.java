package io.kidan.verity.validations.csv;

import io.kidan.guardian.dto.csv.response.CsvContractRule;
import io.kidan.guardian.dto.csv.response.NumericContractRule;

public class CsvNumericRuleValidation implements CsvFileValidation {

    @Override
    public String buildValidationQuery(CsvContractRule csvRule) {
        NumericContractRule numericRule = (NumericContractRule) csvRule;
        return numericRule.getColumnName() + " is between " + numericRule.getMin() +
                " and " + numericRule.getMax();
    }

}

package io.kidan.verity.validations.csv;

import io.kidan.guardian.dto.csv.response.CsvContractRule;
import io.kidan.guardian.dto.csv.response.StringContractRule;

public class CsvStringRuleValidation implements CsvFileValidation {

    @Override
    public String buildValidationQuery (CsvContractRule csvRule) {
        StringContractRule stringRule = (StringContractRule) csvRule;
        if (stringRule.getCleanedKeywords().equals("'-'")) {
            return "";
        }
        return " lower("+ stringRule.getColumnName() + ") not in (" + stringRule.getCleanedKeywords() + ")";
    }

}

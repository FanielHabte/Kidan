package io.kidan.verity.validations.csv;

import io.kidan.guardian.dto.csv.response.CsvContractRule;

public class UniquenessCsvValidationValidation implements CsvFileValidation {

    // qualify row_number() over(partition a order by a)
    @Override
    public String buildValidationQuery(CsvContractRule csvContractRule) {
        return "ROW_NUMBER() OVER(PARTITION BY " + csvContractRule.getColumnName()
                + " ORDER BY " + csvContractRule.getColumnName() + ")";
    }

}

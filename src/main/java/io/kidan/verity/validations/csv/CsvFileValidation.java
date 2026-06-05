package io.kidan.verity.validations.csv;

import io.kidan.guardian.dto.csv.response.CsvContractRule;

public interface CsvFileValidation {
    String buildValidationQuery(CsvContractRule csvRule);
}

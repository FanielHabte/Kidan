package io.kidan.verity.dto;

import io.kidan.verity.enums.Result;
import org.springframework.stereotype.Component;

@Component
public class ValidationResultFactory {

    public ValidationResult buildPassedValidationResult(String columnName) {
        ValidationResult validationResult = new ValidationResult();
        validationResult.setColumn(columnName);
        validationResult.setStatus(Result.PASS);
        validationResult.setIssueType("NONE");
        validationResult.setRowNumber(0);

        return validationResult;
    }

    public ValidationResult buildFailedValidationResult(String columnName, int rowNumber, String ruleType) {
        ValidationResult validationResult = new ValidationResult();
        validationResult.setColumn(columnName);
        validationResult.setStatus(Result.FAIL);
        validationResult.setIssueType(ruleType);
        validationResult.setRowNumber(rowNumber);

        return validationResult;
    }

}

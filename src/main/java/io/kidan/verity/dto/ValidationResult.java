package io.kidan.verity.dto;

import io.kidan.verity.enums.IssueType;
import io.kidan.verity.enums.Result;

public class ValidationResult {
    String column;
    int rowNumber;
    Result status;
    IssueType issueType;

    public ValidationResult() {
    }

    public String getColumn() {
        return column;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public Result getStatus() {
        return status;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public void setStatus(Result status) {
        this.status = status;
    }

    public void setIssueType(String ruleType) {
        this.issueType =
        switch (ruleType){
            case "KEYWORD_RULE" -> IssueType.INVALID_KEYWORD;
            case "RANGE" -> IssueType.OUT_OF_RANGE;
            case "FORMAT_TYPE" -> IssueType.INVALID_FORMAT;
            case "NONE" -> IssueType.NONE;
            default -> throw new RuntimeException("INVALID ISSUE TYPE");
        };

    }
}

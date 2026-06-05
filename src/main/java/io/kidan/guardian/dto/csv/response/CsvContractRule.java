package io.kidan.guardian.dto.csv.response;

import io.kidan.guardian.enums.DataType;
import io.kidan.guardian.enums.RuleType;
import io.kidan.verity.validations.csv.CsvFileValidation;

public abstract class  CsvContractRule  {
    private String columnName;
    private RuleType ruleType;
    private DataType dataType;
    private boolean isUnique;
    private boolean isRequired;
    private CsvFileValidation csvFileValidation;

    public void setCsvFileValidation(CsvFileValidation csvFileValidation) {
        this.csvFileValidation = csvFileValidation;
    }

    public String performCsvFileValidation() {
        return csvFileValidation.buildValidationQuery(this);
    }

    public void setRuleType(RuleType ruleType) {
        this.ruleType = ruleType;
    }

    public RuleType getRuleType() {
        return this.ruleType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public boolean getIsUnique() {
        return isUnique;
    }

    public void setIsUnique(boolean isUnique) {
        this.isUnique = isUnique;
    }

    public boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }

    public abstract String getCustomRuleConfig();
}

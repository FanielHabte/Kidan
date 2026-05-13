package io.kidan.guardian.dto.csv;

import io.kidan.guardian.enums.DataType;
import io.kidan.guardian.enums.RuleType;

import java.io.Serial;
import java.io.Serializable;

public class ContractRuleForm implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String columnName;
    private DataType dataType;
    private RuleType ruleType;
    private boolean isUnique;
    private boolean isRequired;

    ContractRuleForm() {}

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public DataType getDataType() {
        return dataType;
    }

    protected void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public boolean isUnique() {
        return isUnique;
    }

    public void setUnique(boolean unique) {
        isUnique = unique;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public RuleType getRuleType() {
        return ruleType;
    }

    protected void setRuleType(RuleType ruleType) {
        this.ruleType = ruleType;
    }

}

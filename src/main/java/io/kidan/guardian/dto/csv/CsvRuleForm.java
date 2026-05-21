package io.kidan.guardian.dto.csv;

import io.kidan.guardian.enums.DataType;
import io.kidan.guardian.enums.FormatType;
import io.kidan.guardian.enums.RuleType;

import java.io.Serial;
import java.io.Serializable;

/*
    DTO used to collect data of all input fields that a
    user might use when creating a contract rule.
*/

public class CsvRuleForm implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String columnName;
    private DataType dataType;
    private RuleType ruleType;
    private Boolean isUnique;
    private Boolean isRequired;
    // For date/time-stamp type column
    private FormatType formatType;
    // For numeric type column
    private Double min;
    private Double max;
    // For string/text type column
    private String keywords;

    public CsvRuleForm() {}

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

    public RuleType getRuleType() {
        return ruleType;
    }

    public void setRuleType(RuleType ruleType) {
        this.ruleType = ruleType;
    }

    public Boolean getIsUnique() {
        return isUnique;
    }

    public void setIsUnique(Boolean unique) {
        isUnique = unique;
    }

    public Boolean getIsRequired () {
        return isRequired;
    }

    public void setIsRequired (Boolean required) {
        isRequired = required;
    }

    public FormatType getFormatType() {
        return formatType;
    }

    public void setFormatType(FormatType formatType) {
        this.formatType = formatType;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

}

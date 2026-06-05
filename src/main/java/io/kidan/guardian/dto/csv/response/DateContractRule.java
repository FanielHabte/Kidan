package io.kidan.guardian.dto.csv.response;

import io.kidan.guardian.enums.DataType;
import io.kidan.guardian.enums.RuleType;

public class DateContractRule extends CsvContractRule {
    private String formatType;

    public DateContractRule() {
        this.setDataType(DataType.DATE);
        this.setRuleType(RuleType.FORMAT_TYPE);
    }

    public String getFormatType() {
        return formatType;
    }

    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    @Override
    public String getCustomRuleConfig() {
        return this.formatType;
    }

    @Override
    public void setRuleType(RuleType ruleType) {
        super.setRuleType(RuleType.FORMAT_TYPE);
    }

    @Override
    public void setDataType(DataType dataType) {
        super.setDataType(DataType.DATE);
    }
}

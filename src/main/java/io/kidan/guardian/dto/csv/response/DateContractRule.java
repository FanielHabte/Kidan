package io.kidan.guardian.dto.csv.response;

import io.kidan.guardian.enums.RuleType;

public class DateContractRule extends CsvContractRule {
    private String formatType;

    public DateContractRule() {
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
    public RuleType getRuleType() {
        return RuleType.FORMAT_TYPE;
    }
}

package io.kidan.guardian.dto.csv.response;

import io.kidan.guardian.enums.RuleType;

public class StringContractRule extends CsvContractRule {
    private String keywords;

    public StringContractRule() {
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String getCustomRuleConfig() {
        return "[ " + this.keywords + " ]";
    }

    @Override
    public RuleType getRuleType() {
        return RuleType.KEYWORD_RULE;
    }

}

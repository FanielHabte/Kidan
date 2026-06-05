package io.kidan.guardian.dto.csv.response;

import io.kidan.guardian.enums.DataType;
import io.kidan.guardian.enums.RuleType;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringContractRule extends CsvContractRule {
    private String keywords;

    public StringContractRule() {
        this.setDataType(DataType.STRING);
        this.setRuleType(RuleType.KEYWORD_RULE);
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
    public void setRuleType(RuleType ruleType) {
        super.setRuleType(RuleType.KEYWORD_RULE);
    }

    @Override
    public void setDataType(DataType dataType) {
        super.setDataType(DataType.STRING);
    }

    public String getCleanedKeywords() {
        String[] keywords = this.keywords.replaceAll("\\s+", "").split(",");

        return Arrays.stream(keywords)
                .filter(s -> !s.isEmpty())
                .map(s -> "'" + s.toLowerCase().trim() + "'")
                .collect(Collectors.joining(","));
    }

}

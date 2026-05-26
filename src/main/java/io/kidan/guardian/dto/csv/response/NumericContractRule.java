package io.kidan.guardian.dto.csv.response;

import io.kidan.guardian.enums.RuleType;

public class NumericContractRule extends CsvContractRule {
    private double min;
    private double max;

    public NumericContractRule() {
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    @Override
    public String getCustomRuleConfig() {
        return "{ Min:" + this.min + ", Max: " + this.max + "}";
    }

    @Override
    public RuleType getRuleType() {
        return RuleType.RANGE;
    }

}

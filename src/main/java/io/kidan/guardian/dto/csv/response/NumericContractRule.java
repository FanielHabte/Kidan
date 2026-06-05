package io.kidan.guardian.dto.csv.response;

import io.kidan.guardian.enums.DataType;
import io.kidan.guardian.enums.RuleType;

public class NumericContractRule extends CsvContractRule {
    private double min;
    private double max;

    public NumericContractRule() {
        this.setDataType(DataType.INT);
        this.setRuleType(RuleType.RANGE);
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
    public void setRuleType(RuleType ruleType) {
        super.setRuleType(RuleType.RANGE);
    }

    @Override
    public void setDataType(DataType dataType) {
        super.setDataType(DataType.INT);
    }
}

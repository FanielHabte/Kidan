package io.kidan.guardian.dto.csv;

import java.io.Serial;
import java.io.Serializable;

public class ContractRuleFormNumeric  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Double min;
    private Double max;

    public ContractRuleFormNumeric() {
//        setDataType(DataType.INT);
//        setRuleType(RuleType.RANGE);
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
}

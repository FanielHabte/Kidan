package io.kidan.guardian.dto.csv;

import io.kidan.guardian.enums.DataType;
import io.kidan.guardian.enums.RuleType;

import java.io.Serial;
import java.io.Serializable;

public class ContractRuleFormNumeric extends ContractRuleForm implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String min;
    private String max;

    public ContractRuleFormNumeric() {
        setDataType(DataType.INT);
        setRuleType(RuleType.RANGE);
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }
}

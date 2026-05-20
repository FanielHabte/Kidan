package io.kidan.guardian.dto.csv;

import io.kidan.guardian.enums.FormatType;

import java.io.Serial;
import java.io.Serializable;

public class ContractRuleFormDate implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private FormatType formatType;

    public ContractRuleFormDate() {
//        setRuleType(RuleType.FORMAT_TYPE);
    }

    public FormatType getFormatType() {
        return formatType;
    }

    public void setFormatType(FormatType formatType) {
        this.formatType = formatType;
    }
}
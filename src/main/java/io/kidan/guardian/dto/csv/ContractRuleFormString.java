package io.kidan.guardian.dto.csv;

import io.kidan.guardian.enums.DataType;
import io.kidan.guardian.enums.RuleType;

import java.io.Serial;
import java.io.Serializable;

public class ContractRuleFormString extends ContractRuleForm implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String keywords;

    public ContractRuleFormString() {
        setDataType(DataType.STRING);
        setRuleType(RuleType.KEYWORD_RULE);
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

}



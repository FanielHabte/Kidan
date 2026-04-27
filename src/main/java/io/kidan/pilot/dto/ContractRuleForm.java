package io.kidan.pilot.dto;

import io.kidan.guardian.entity.Contract;
import io.kidan.guardian.enums.RuleType;

public class ContractRuleForm {
    private Contract contract;
    private RuleType ruleType;
    private String ruleConfig;
    private boolean isRequired;
}


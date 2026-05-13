package io.kidan.guardian.dto.csv;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContractRuleFormWrapper implements Serializable {
    @Serial
    private static final long serialVersionUID = -2388477470216719934L;
    private ContractFileStructure contractFileStructure;
    private List<ContractRuleForm> contractRuleFormList = new ArrayList<>();

    public ContractRuleFormWrapper () {}

    public ContractRuleFormWrapper ( ContractFileStructure contractFileStructure, List<ContractRuleForm> contractRuleFormList) {
        this.contractRuleFormList = contractRuleFormList;
        this.contractFileStructure = contractFileStructure;
    }

    public ContractFileStructure getContractFileStructure() {
        return contractFileStructure;
    }

    public void setContractFileStructure(ContractFileStructure contractFileStructure) {
        this.contractFileStructure = contractFileStructure;
    }

    public List<ContractRuleForm> getContractRuleFormList() {
        return contractRuleFormList;
    }

    public void setContractRuleFormList(List<ContractRuleForm> contractRuleFormList) {
        this.contractRuleFormList = contractRuleFormList;
    }
}

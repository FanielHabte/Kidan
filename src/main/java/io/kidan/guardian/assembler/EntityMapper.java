package io.kidan.guardian.assembler;

import io.kidan.guardian.dto.csv.CsvFileStructure;
import io.kidan.guardian.dto.csv.CsvRuleForm;
import io.kidan.guardian.entity.Contract;
import io.kidan.guardian.entity.ContractRule;
import io.kidan.guardian.enums.DataType;
import io.kidan.guardian.enums.RuleType;
import io.kidan.guardian.service.serializer.CsvSerializer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityMapper {
    private final CsvSerializer csvSerializer;

    public EntityMapper(CsvSerializer csvSerializer) {
        this.csvSerializer = csvSerializer;
    }

    public ContractRule buildContractRule(CsvRuleForm csvRuleForm, Contract contract) {
        ContractRule contractRule = new ContractRule();
        DataType dataType = csvRuleForm.getDataType();

        if (dataType == DataType.STRING) {
            contractRule.setRuleType(RuleType.KEYWORD_RULE);
        } else if (dataType == DataType.DATE || dataType == DataType.TIMESTAMP) {
            contractRule.setRuleType(RuleType.FORMAT_TYPE);
        } else if (dataType == DataType.INT || dataType == DataType.DECIMAL) {
            contractRule.setRuleType(RuleType.RANGE);
        }
        contractRule.setContract(contract);
        contractRule.setRuleConfig(csvSerializer.getRuleConfig(csvRuleForm));

        return contractRule;
    }

    public Contract buildContract(CsvFileStructure csvFileStructure) {
        Contract contract = new Contract();
        contract.setName(csvFileStructure.getContractName());
        contract.setDescription(csvFileStructure.getDescription());
        contract.setContractConfig(csvSerializer.getContractConfig(csvFileStructure));

        return contract;
    }

    public List<ContractRule> getContractRuleList(List<CsvRuleForm> csvFormList, Contract contract) {
        List<ContractRule> contractRuleList = new ArrayList<>();
        for (CsvRuleForm csvRuleForm : csvFormList) {
            contractRuleList.add(buildContractRule(csvRuleForm, contract));
        }

        return contractRuleList;
    }



}

package io.kidan.guardian.factory;

import io.kidan.guardian.entity.contract.Contract;
import io.kidan.guardian.entity.contractRule.ContractRule;
import io.kidan.guardian.entity.contract.CsvContract;
import io.kidan.guardian.enums.DataType;
import io.kidan.guardian.enums.RuleType;
import io.kidan.guardian.dto.csv.CsvFileStructure;
import io.kidan.guardian.dto.csv.CsvRuleForm;
import io.kidan.guardian.service.csv.CsvSerializer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContractRuleFactory {
    private final CsvSerializer csvSerializer;

    public ContractRuleFactory (CsvSerializer csvSerializer) {
        this.csvSerializer = csvSerializer;
    }

    public ContractRule buildContractRule(CsvRuleForm csvRuleForm, Contract contract) {
        ContractRule contractRule = new ContractRule();
        DataType dataType =  csvRuleForm.getDataType();

        if (dataType == DataType.STRING) {
            contractRule.setRuleType(RuleType.KEYWORD_RULE);
        } else if (dataType == DataType.DATE || dataType == DataType.TIMESTAMP) {
            contractRule.setRuleType(RuleType.FORMAT_TYPE);
        } else if (dataType == DataType.INT || dataType == DataType.DECIMAL) {
            contractRule.setRuleType(RuleType.RANGE);
        }

        contractRule.setContract(contract);
        contractRule.setRequired(csvRuleForm.getIsRequired());
        contractRule.setUnique(csvRuleForm.getIsUnique());
        contractRule.setRuleConfig(csvSerializer.getRuleConfig(csvRuleForm));

        return contractRule;
    }

    public List<ContractRule> getContractRuleList (List<CsvRuleForm>  csvFormList, Contract contract) {
        List<ContractRule> contractRuleList = new ArrayList<>();
        for (CsvRuleForm csvRuleForm: csvFormList) {
            contractRuleList.add(buildContractRule(csvRuleForm, contract));
        }

        return  contractRuleList;
    }

    public CsvContract buildCsvContract (CsvFileStructure csvFileStructure, CsvContract csvContract) {
        csvContract.setContractName(csvFileStructure.getContractName());
        csvContract.setColumnNames(csvFileStructure.getColumnNames());
        csvContract.setDescription(csvFileStructure.getDescription());

        return csvContract;
    }

}

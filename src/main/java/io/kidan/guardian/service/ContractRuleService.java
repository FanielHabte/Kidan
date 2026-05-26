package io.kidan.guardian.service;

import io.kidan.guardian.assembler.EntityMapper;
import io.kidan.guardian.dto.csv.response.CsvValidationObject;
import io.kidan.guardian.entity.ContractRule;
import io.kidan.guardian.repository.ContractRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractRuleService {
    private final ContractRuleRepository contractRuleRepository;
    private final EntityMapper entityMapper;

    public ContractRuleService(ContractRuleRepository contractRuleRepository, EntityMapper entityMapper) {
        this.contractRuleRepository = contractRuleRepository;
        this.entityMapper = entityMapper;
    }

    public ContractRule findContractRuleById(String id) throws RuntimeException {
        return contractRuleRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Contract rule id: [" + id + "] no found"));
    }

    public List<ContractRule> findAllContractRules() {
        return contractRuleRepository.findAll();
    }

    public List<ContractRule> findContractRulesById(List<String> idList) {
        return contractRuleRepository.findAllById(idList);
    }

    public List<ContractRule> findContractRulesByContractId(String contractId) {
        return contractRuleRepository.findAllByContractId(contractId);
    }

    public void saveContractRule(ContractRule contractRule) {
        contractRuleRepository.save(contractRule);
    }

    public void saveAllContractRules(List<ContractRule> contractRuleList) {
        contractRuleRepository.saveAll(contractRuleList);
    }

    public List<CsvValidationObject> findAllCsvValidationObjectsByContractId (String contractId) {
        List<ContractRule> contractRuleList = findContractRulesByContractId(contractId);

        return entityMapper.buildCsvValidationObjects(contractRuleList);
    }

}

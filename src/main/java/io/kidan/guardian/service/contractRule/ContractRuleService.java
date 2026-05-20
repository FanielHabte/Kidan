package io.kidan.guardian.service.contractRule;

import io.kidan.guardian.entity.contractRule.ContractRule;
import io.kidan.guardian.repository.contractRule.ContractRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractRuleService {
    private final ContractRuleRepository contractRuleRepository;

    public ContractRuleService(ContractRuleRepository contractRuleRepository) {
        this.contractRuleRepository = contractRuleRepository;
    }

    public ContractRule findContractById(String id) throws RuntimeException {
        return contractRuleRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Contract rule id: [" + id + "] no found"));
    }

    public List<ContractRule> findAllContractRules() {
        return contractRuleRepository.findAll();
    }

    public List<ContractRule> findContractRulesById(List<String> idList) {
        return contractRuleRepository.findAllById(idList);
    }

    public void saveContractRule(ContractRule contractRule) {
        contractRuleRepository.save(contractRule);
    }

    public void saveAllContractRules(List<ContractRule> contractRuleList) {
        contractRuleRepository.saveAll(contractRuleList);
    }

}

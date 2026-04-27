package io.kidan.guardian.service;

import io.kidan.guardian.entity.Contract;
import io.kidan.guardian.entity.ContractRule;
import io.kidan.guardian.entity.Dataset;
import io.kidan.guardian.repository.ContractRepository;
import io.kidan.guardian.repository.ContractRuleRepository;
import io.kidan.guardian.repository.DatasetRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuardianService {

    private final DatasetRepository datasetRepository;
    private final ContractRepository contractRepository;
    private final ContractRuleRepository contractRuleRepository;

    GuardianService(DatasetRepository datasetRepository,
                    ContractRepository contractRepository,
                    ContractRuleRepository contractRuleRepository)
    {
        this.datasetRepository = datasetRepository;
        this.contractRepository = contractRepository;
        this.contractRuleRepository = contractRuleRepository;
    }

    public List<Dataset> getAllDataSets () {
        return datasetRepository.findAll();
    }

    public void createDatasetAndContract (@NonNull Dataset dataset, @NonNull Contract contract, @NonNull ContractRule contractRule) {
        datasetRepository.save(dataset);
        contract.setDataset(dataset);
        contractRepository.save(contract);
        contractRule.setContract(contract);
        contractRuleRepository.save(contractRule);
    }

}

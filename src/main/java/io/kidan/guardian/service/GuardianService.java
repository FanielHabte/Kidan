package io.kidan.guardian.service;

import io.kidan.guardian.entity.Dataset;
import io.kidan.guardian.repository.ContractRepository;
import io.kidan.guardian.repository.ContractRuleRepository;
import io.kidan.guardian.repository.DatasetRepository;
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

    public void createDataset (Dataset newDataset) {
        datasetRepository.save(newDataset);
    }

}

package io.kidan.guardian.service;

import io.kidan.guardian.entity.Contract;
import io.kidan.guardian.entity.ContractRule;
import io.kidan.guardian.entity.Dataset;
import io.kidan.guardian.factory.ContractRuleFactory;
import io.kidan.guardian.repository.ContractRepository;
import io.kidan.guardian.repository.ContractRuleRepository;
import io.kidan.guardian.repository.DatasetRepository;
import io.kidan.guardian.validation.CsvValidator;
import io.kidan.guardian.web.dto.csv.CsvFormWrapper;
import io.kidan.nexus.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CsvFileService {

    private final CsvValidator csvValidator;
    private final ContractRuleFactory contractRuleFactory;
    private final ContractRepository contractRepository;
    private final ContractRuleRepository contractRuleRepository;
    private final DatasetRepository datasetRepository;

    CsvFileService(CsvValidator csvValidator
            , ContractRuleFactory contractRuleFactory
            , ContractRepository contractRepository
            , ContractRuleRepository contractRuleRepository
            , DatasetRepository datasetRepository) {

        this.datasetRepository = datasetRepository;
        this.csvValidator = csvValidator;
        this.contractRuleFactory = contractRuleFactory;
        this.contractRepository = contractRepository;
        this.contractRuleRepository = contractRuleRepository;
    }

    public void saveCsvFile(User currentUser, Dataset dataset, Contract contract, CsvFormWrapper wrapper) {
        boolean isValid = csvValidator.fileValidation(wrapper);
        List<ContractRule> contractRuleList = contractRuleFactory.getContractRuleList(wrapper.getCsvRuleFormList(), contract);

        if (isValid) {
            dataset.setUser(currentUser);
            datasetRepository.save(dataset);

            contract.setDataset(dataset);
            contractRepository.save(contract);

            contractRuleRepository.saveAll(contractRuleList);
        } else {
            throw new RuntimeException("Error occurred");
        }
    }

}

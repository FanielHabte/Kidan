package io.kidan.guardian.service.general;

import io.kidan.guardian.dto.csv.CsvFormWrapper;
import io.kidan.guardian.entity.contract.Contract;
import io.kidan.guardian.entity.contract.CsvContract;
import io.kidan.guardian.entity.contractRule.ContractRule;
import io.kidan.guardian.entity.dataset.Dataset;
import io.kidan.guardian.factory.ContractRuleFactory;
import io.kidan.guardian.service.contract.ContractService;
import io.kidan.guardian.service.contractRule.ContractRuleService;
import io.kidan.guardian.service.csv.CsvContractService;
import io.kidan.guardian.service.dataset.DatasetService;
import io.kidan.guardian.validation.CsvValidator;
import jakarta.transaction.Transactional;

import java.util.List;

public class FileStorageService {
    private final CsvValidator csvValidator;
    private final ContractRuleFactory contractRuleFactory;
    private final ContractService contractService;
    private final ContractRuleService contractRuleService;
    private final DatasetService datasetService;
    private final CsvContractService csvContractService;

    FileStorageService(CsvValidator csvValidator, ContractRuleFactory contractRuleFactory
                    , ContractService contractService, ContractRuleService contractRuleService
                    , DatasetService datasetService, CsvContractService csvContractService) {
        this.datasetService = datasetService;
        this.csvValidator = csvValidator;
        this.contractRuleFactory = contractRuleFactory;
        this.contractService = contractService;
        this.contractRuleService = contractRuleService;
        this.csvContractService = csvContractService;
    }

    @Transactional
    public void saveCsvFile(Dataset dataset, CsvFormWrapper wrapper) {
        boolean isValid = csvValidator.fileValidation(wrapper);
        Contract contract = new Contract();
        CsvContract csvContract = contractRuleFactory.buildCsvContract(wrapper.getCsvFileStructure(), new CsvContract());
        List<ContractRule> contractRuleList = contractRuleFactory.getContractRuleList(wrapper.getCsvRuleFormList(), contract);
        if (isValid) {
            datasetService.saveDataset(dataset);
            contractService.saveContract(contract, dataset);
            csvContractService.saveCsvContractRule(csvContract, contract);
            contractRuleService.saveAllContractRules(contractRuleList);
        } else {
            throw new RuntimeException("Error occurred");
        }
    }

}

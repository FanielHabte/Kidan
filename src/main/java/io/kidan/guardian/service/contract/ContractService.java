package io.kidan.guardian.service.contract;

import io.kidan.guardian.entity.contract.Contract;
import io.kidan.guardian.entity.contract.ContractsPageView;
import io.kidan.guardian.entity.dataset.Dataset;
import io.kidan.guardian.repository.contract.ContractRepository;
import io.kidan.guardian.repository.contract.ContractsPageViewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {
    private final ContractRepository contractRepository;
    private final ContractsPageViewRepository contractsPageViewRepository;

    public ContractService(ContractRepository contractRepository, ContractsPageViewRepository contractsPageViewRepository) {
        this.contractRepository = contractRepository;
        this.contractsPageViewRepository = contractsPageViewRepository;
    }

    public Contract findContractById(String id) throws RuntimeException {
        return contractRepository.findByIdWithRulesAndCsvContracts(id).orElseThrow(
                () -> new RuntimeException("Contract id: [" + id + "] no found")
        );
    }

    public List<Contract> findContractsById(List<String> ids) {
        return contractRepository.findAllById(ids);
    }

    public List<Contract> findAllContracts() {
        return contractRepository.findAll();
    }

    public List<ContractsPageView> findAllContractPageSummaries () {
        return contractsPageViewRepository.findAll();
    }

    public void saveContract(Contract contract, Dataset dataset) {
        contract.setDataset(dataset);
        contractRepository.save(contract);
    }

    public void saveAllContracts(List<Contract> contractList) {
        contractRepository.saveAll(contractList);
    }
}

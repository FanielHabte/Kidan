package io.kidan.guardian.service;

import io.kidan.fortress.service.UserAuthService;
import io.kidan.guardian.entity.Contract;
import io.kidan.guardian.entity.contract.ContractsPageView;
import io.kidan.guardian.entity.Dataset;
import io.kidan.guardian.repository.ContractRepository;
import io.kidan.guardian.repository.views.ContractsPageViewRepository;
import io.kidan.nexus.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {
    private final ContractRepository contractRepository;
    private final ContractsPageViewRepository contractsPageViewRepository;
    private final UserAuthService userAuthService;

    public ContractService(ContractRepository contractRepository,
                           ContractsPageViewRepository contractsPageViewRepository,
                           UserAuthService userAuthService) {
        this.contractRepository = contractRepository;
        this.contractsPageViewRepository = contractsPageViewRepository;
        this.userAuthService = userAuthService;
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
        User user = userAuthService.AuthenticatedUser()
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Authenticated User not found"
                        ));
        contract.setDataset(dataset);
        contract.setUpdatedBy(user);
        contractRepository.save(contract);
    }

    public void saveAllContracts(List<Contract> contractList) {
        contractRepository.saveAll(contractList);
    }
}

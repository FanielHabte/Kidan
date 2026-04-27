package io.kidan.guardian.service;

import io.kidan.fortress.service.UserAuthService;
import io.kidan.guardian.entity.Contract;
import io.kidan.guardian.entity.ContractRule;
import io.kidan.guardian.entity.Dataset;
import io.kidan.guardian.repository.ContractRepository;
import io.kidan.guardian.repository.ContractRuleRepository;
import io.kidan.guardian.repository.DatasetRepository;
import io.kidan.nexus.entity.User;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuardianService {

    private final DatasetRepository datasetRepository;
    private final ContractRepository contractRepository;
    private final ContractRuleRepository contractRuleRepository;
    private final UserAuthService userAuthService;

    GuardianService(DatasetRepository datasetRepository,
                    ContractRepository contractRepository,
                    ContractRuleRepository contractRuleRepository,
                    UserAuthService userAuthService)
    {
        this.datasetRepository = datasetRepository;
        this.contractRepository = contractRepository;
        this.contractRuleRepository = contractRuleRepository;
        this.userAuthService = userAuthService;
    }

    public List<Dataset> getAllDataSets () {
        return datasetRepository.findAll();
    }

    public void createDatasetAndContract (@NonNull Dataset dataset, @NonNull Contract contract, @NonNull ContractRule contractRule) throws UsernameNotFoundException {
        User currentUser = userAuthService
            .AuthenticatedUser()
            .orElseThrow(
                    () -> new UsernameNotFoundException (
                            "Authenticated User not found"
                    )
            );

        dataset.setUser(currentUser);
        datasetRepository.save(dataset);

        contract.setDataset(dataset);
        contractRepository.save(contract);

        contractRule.setContract(contract);
        contractRuleRepository.save(contractRule);
    }

}

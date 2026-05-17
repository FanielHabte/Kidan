package io.kidan.guardian.service;

import io.kidan.fortress.service.UserAuthService;
import io.kidan.guardian.entity.ContractsPageView;
import io.kidan.guardian.entity.Dataset;
import io.kidan.guardian.repository.ContractRepository;
import io.kidan.guardian.repository.ContractsPageViewRepository;
import io.kidan.guardian.repository.DatasetRepository;
import io.kidan.guardian.web.dto.csv.CsvFormWrapper;
import io.kidan.nexus.entity.User;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuardianService {

    private  final CsvFileService csvFileService;
    private final UserAuthService userAuthService;
    private final DatasetRepository datasetRepository;
    private final ContractRepository contractRepository;
    private final ContractsPageViewRepository contractsPageViewRepository;

    GuardianService(CsvFileService csvFileService, UserAuthService userAuthService, DatasetRepository datasetRepository, ContractRepository contractRepository, ContractsPageViewRepository contractsPageViewRepository) {
        this.csvFileService = csvFileService;
        this.userAuthService = userAuthService;
        this.datasetRepository = datasetRepository;
        this.contractRepository = contractRepository;
        this.contractsPageViewRepository = contractsPageViewRepository;
    }

    public List<Dataset> getAllDataSets() {
        String userId = userAuthService
                .AuthenticatedUser()
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Authenticated User not found"
                        )
                ).getId();

        return datasetRepository.findAllByUserId(userId);
    }

    public void createDatasetAndContract(@NonNull Dataset dataset, @NonNull CsvFormWrapper wrapper) throws UsernameNotFoundException {
        User currentUser = userAuthService
                .AuthenticatedUser()
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Authenticated User not found"
                        )
                );
        csvFileService.saveCsvFile(currentUser, dataset, wrapper);
    }

    public Dataset getDatasetById(String id) throws RuntimeException {
        String userId = userAuthService
                .AuthenticatedUser()
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Authenticated User not found"
                        )
                ).getId();

        return datasetRepository.findByIdAndUserId(id, userId).orElseThrow(
                () -> new RuntimeException("Dataset was not found")
        );
    }

    public void editDataset(String datasetId, String newName) throws RuntimeException {
        Dataset dataset = datasetRepository.findById(datasetId).orElseThrow(
                () -> new RuntimeException("Dataset not found!")
        );
        User currentUser = userAuthService
                .AuthenticatedUser()
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Authenticated User not found"
                        )
                );
        dataset.setName(newName);
        dataset.setUpdatedBy(currentUser);

        datasetRepository.save(dataset);
    }

    public List<ContractsPageView> contractsPageViewList (){
        ContractsPageView contractsPageView = new ContractsPageView();

        return contractsPageViewRepository.findAll();
    }

}

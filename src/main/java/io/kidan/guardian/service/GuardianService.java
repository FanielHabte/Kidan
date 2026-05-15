package io.kidan.guardian.service;

import io.kidan.fortress.service.UserAuthService;
import io.kidan.guardian.entity.Contract;
import io.kidan.guardian.entity.Dataset;
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

    GuardianService(CsvFileService csvFileService, UserAuthService userAuthService, DatasetRepository datasetRepository) {
        this.csvFileService = csvFileService;
        this.userAuthService = userAuthService;
        this.datasetRepository = datasetRepository;
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

    public void createDatasetAndContract(@NonNull Dataset dataset, @NonNull Contract contract, @NonNull CsvFormWrapper wrapper) throws UsernameNotFoundException {
        User currentUser = userAuthService
                .AuthenticatedUser()
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Authenticated User not found"
                        )
                );
        csvFileService.saveCsvFile(currentUser, dataset, contract, wrapper);
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

}

package io.kidan.guardian.service.dataset;

import io.kidan.fortress.service.UserAuthService;
import io.kidan.guardian.entity.dataset.Dataset;
import io.kidan.guardian.repository.dataset.DatasetRepository;
import io.kidan.nexus.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatasetService {
    private final DatasetRepository datasetRepository;
    private final UserAuthService userAuthService;

    public DatasetService(DatasetRepository datasetRepository, UserAuthService userAuthService) {
        this.datasetRepository = datasetRepository;
        this.userAuthService = userAuthService;
    }

    public List<Dataset> findAllDataSets() {
        String userId = userAuthService.AuthenticatedUser()
                        .orElseThrow(
                                () -> new UsernameNotFoundException(
                                        "Authenticated User not found"
                        )).getId();
        return datasetRepository.findAllByUserId(userId);
    }

    public Dataset findDatasetById(String id) throws RuntimeException {
        return datasetRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Dataset id: [" + id + "] no found")
        );
    }

    public List<Dataset> findDatasetById(List<String> idList) {
        return datasetRepository.findAllById(idList);
    }

    public void saveDataset(Dataset dataset) {
        User user = userAuthService.AuthenticatedUser()
                    .orElseThrow(
                            () -> new UsernameNotFoundException(
                                    "Authenticated User not found"
                    ));
        dataset.setUser(user);
        datasetRepository.save(dataset);
    }

    public void saveAllDatasets(List<Dataset> datasetList) {
        User user = userAuthService.AuthenticatedUser()
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Authenticated User not found"
                        ));
        datasetList.forEach(dataset -> {
            dataset.setUser(user);
            dataset.setUpdatedBy(user);
        });
        datasetRepository.saveAll(datasetList);
    }

    public void updateDatasetNameById (String datasetId, String newName) throws RuntimeException {
        Dataset dataset = findDatasetById(datasetId);
        User currentUser = userAuthService.AuthenticatedUser()
                            .orElseThrow(
                                    () -> new UsernameNotFoundException(
                                            "Authenticated User not found"
                            ));
        dataset.setName(newName);
        dataset.setUpdatedBy(currentUser);
        saveDataset(dataset);
    }
}

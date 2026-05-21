package io.kidan.inlet.service;

import io.kidan.guardian.entity.Dataset;
import io.kidan.guardian.service.DatasetService;
import io.kidan.inlet.entity.Submission;
import io.kidan.inlet.repository.InletRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InletService {

    private final DatasetService datasetService;
    private final InletRepository inletRepository;

    InletService (DatasetService datasetService, InletRepository inletRepository) {
        this.datasetService = datasetService;
        this.inletRepository = inletRepository;
    }

    public List<Submission> getSubmissionList() {

        return inletRepository.findAll();
    }

    public Submission getSubmissionById(String id) {

        return inletRepository.findById(id).orElseThrow(
                () -> new RuntimeException("submission id: "+ id + " not found")
        );
    }

    public List<Dataset> getDatasetList() {

        return datasetService.findAllDataSets();
    }

}

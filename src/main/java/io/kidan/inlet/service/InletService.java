package io.kidan.inlet.service;

import io.kidan.guardian.entity.dataset.Dataset;
import io.kidan.inlet.entity.Submission;
import io.kidan.inlet.repository.InletRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InletService {

    private final GuardianService guardianService;
    private final InletRepository inletRepository;

    InletService (GuardianService guardianService, InletRepository inletRepository) {
        this.guardianService = guardianService;
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

        return guardianService.getAllDataSets();
    }

}

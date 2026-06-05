package io.kidan.inlet.service;

import com.opencsv.exceptions.CsvValidationException;
import io.kidan.fortress.service.UserAuthService;
import io.kidan.guardian.entity.Dataset;
import io.kidan.guardian.service.DatasetService;
import io.kidan.inlet.entity.Submission;
import io.kidan.inlet.repository.InletRepository;
import io.kidan.nexus.entity.User;
import io.kidan.verity.dto.ValidationResult;
import io.kidan.verity.enums.IssueType;
import io.kidan.verity.service.VerityService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Service
public class InletService {

    private final InletRepository inletRepository;
    private final UserAuthService userAuthService;
    private final DatasetService datasetService;
    private final FilesStorageService filesStorageService;
    private final VerityService verityService;

    InletService(InletRepository inletRepository, UserAuthService userAuthService, DatasetService datasetService, FilesStorageService filesStorageService, VerityService verityService) {
        this.inletRepository = inletRepository;
        this.userAuthService = userAuthService;
        this.datasetService = datasetService;
        this.filesStorageService = filesStorageService;
        this.verityService = verityService;
    }

    public List<Submission> findAllSubmissions() {
        return inletRepository.findAll();
    }

    public Submission findSubmissionById(String id) {
        return inletRepository.findById(id).orElseThrow(
                () -> new RuntimeException("submission id: " + id + " not found")
        );
    }

    public void saveAllSubmissions(List<Submission> submissionList) {
        inletRepository.saveAll(submissionList);
    }

    public void saveSubmission(Submission submission, MultipartFile inputFile) throws IOException, SQLException, CsvValidationException {
        String filePath = filesStorageService.saveFile(inputFile);
        List<ValidationResult> validationResultList = verityService.validateSubmission(filePath, submission.getDataset());
        long failedValidations = validationResultList
                .stream()
                .filter(validationResult -> !validationResult.getIssueType().equals(IssueType.NONE))
                .count();
        if (failedValidations == 0) {
            Submission mappedSubmission = createSubmission(submission, inputFile);
            inletRepository.save(mappedSubmission);
        }
    }

    public Submission createSubmission(Submission submission, MultipartFile inputFile) {
        HashMap<String, String> fileDetails = filesStorageService.getFileDetails(inputFile);
        User user = userAuthService.AuthenticatedUser().orElseThrow(
                () -> new RuntimeException("User not found")
        );
        Dataset dataset = datasetService.findDatasetById(submission.getDataset().getId());

        submission.setDataset(dataset);
        submission.setFileName(fileDetails.get("fileName"));
        submission.setFilePath(fileDetails.get("filePath"));
        submission.setUser(user);

        return submission;
    }

    public List<Submission> findAllSubmissionByDatasetId(String datasetId) {
        return inletRepository.findAllByDatasetId(datasetId);
    }

}

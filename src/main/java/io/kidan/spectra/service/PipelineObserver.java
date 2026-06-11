package io.kidan.spectra.service;

import io.kidan.inlet.entity.Submission;
import io.kidan.spectra.entity.PipelineRun;
import io.kidan.verity.dto.ValidationResult;
import io.kidan.verity.enums.IssueType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PipelineObserver {

    private final PiplineService piplineService;

    public PipelineObserver(PiplineService piplineService) {
        this.piplineService = piplineService;
    }

    public void update(List<ValidationResult> validationResultList, Submission submission){
        PipelineRun pipelineRun = buildPipeline(validationResultList, submission);
        piplineService.savePiplineRun(pipelineRun);
    }

    public PipelineRun buildPipeline(List<ValidationResult> validationResultList, Submission submission){
        PipelineRun pipelineRun = new PipelineRun();
        int failedValidations = (int) validationResultList
                .stream()
                .filter(validationResult -> !validationResult.getIssueType().equals(IssueType.NONE))
                .count();
        int passedValidations = (int) validationResultList
                .stream()
                .filter(validationResult -> validationResult.getIssueType().equals(IssueType.NONE))
                .count();
        int healthScore;
        if (failedValidations == 0){
            healthScore = 100;
        }
        else {
            healthScore = Math.round((float) failedValidations * 100 / validationResultList.size());
        }

        pipelineRun.setFailedCount(failedValidations);
        pipelineRun.setPassedCount(passedValidations);
        pipelineRun.setHealthScore(healthScore);
        pipelineRun.setWarnedCount(0);
        pipelineRun.setSubmission(submission);

        return pipelineRun;
    }


}

package validation.entity;

import jakarta.persistence.*;
import submission.entity.Submission;

import java.time.LocalDateTime;

public class PipelineRun {
    @Id @GeneratedValue (strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn (name = "submission_id")
    private Submission submissionId;
    private int healthScore;
    private int passedCount;
    private int failedCount;
    private int warnedCount;
    @Column (updatable = false)
    private LocalDateTime createdAt;

    public PipelineRun() {
    }

    public String getId() {
        return id;
    }

    public Submission getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Submission submissionId) {
        this.submissionId = submissionId;
    }

    public int getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(int healthScore) {
        this.healthScore = healthScore;
    }

    public int getPassedCount() {
        return passedCount;
    }

    public void setPassedCount(int passedCount) {
        this.passedCount = passedCount;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
    }

    public int getWarnedCount() {
        return warnedCount;
    }

    public void setWarnedCount(int warnedCount) {
        this.warnedCount = warnedCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

}



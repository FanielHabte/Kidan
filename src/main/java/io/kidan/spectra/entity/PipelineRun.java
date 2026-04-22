package io.kidan.spectra.entity;

import jakarta.persistence.*;
import io.kidan.inlet.entity.Submission;

import java.time.LocalDateTime;

@Entity
@Table (catalog = "kidan", schema = "spectra",
        indexes = {
            @Index(name = "idx_pipeline_submission", columnList = "submission_id")
        }
)
public class PipelineRun {

    @Id @GeneratedValue (strategy = GenerationType.UUID)
    private String id;
    @ManyToOne @JoinColumn (name = "submission_id", nullable = false)
    private Submission submission;
    @Column (nullable = false)
    private int healthScore;
    @Column (nullable = false)
    private int passedCount;
    @Column (nullable = false)
    private int failedCount;
    @Column (nullable = false)
    private int warnedCount;
    @Column (updatable = false, nullable = false)
    private LocalDateTime createdAt;

    public PipelineRun() {

    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
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

}



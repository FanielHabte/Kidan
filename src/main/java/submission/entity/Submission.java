package submission.entity;

import contract.entity.Dataset;
import gateway.entity.User;
import jakarta.persistence.*;
import submission.enums.SubmissionStatus;

import java.time.LocalDateTime;

public class Submission {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn (name = "dataset_id")
    private Dataset datasetId;
    private String fileName;
    private String filePath;
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User userId;
    private SubmissionStatus status;
    private boolean qaEnabled;
    private LocalDateTime createdAt;

    public Submission() {
    }

    public String getId() {
        return id;
    }

    public Dataset getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(Dataset datasetId) {
        this.datasetId = datasetId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public SubmissionStatus getStatus() {
        return status;
    }

    public void setStatus(SubmissionStatus status) {
        this.status = status;
    }

    public boolean isQaEnabled() {
        return qaEnabled;
    }

    public void setQaEnabled(boolean qaEnabled) {
        this.qaEnabled = qaEnabled;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

}



package inlet.entity;

import guardian.entity.Dataset;
import nexus.entity.User;
import jakarta.persistence.*;
import inlet.enums.SubmissionStatus;

import java.time.LocalDateTime;

@Entity
@Table (catalog = "kidan", schema = "inlet",
        indexes = {
            @Index(name = "idx_submission_dataset", columnList = "dataset_id"),
            @Index(name = "idx_submission_user", columnList = "user_id"),
        }
)
public class Submission {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne @JoinColumn (name = "dataset_id", nullable = false)
    private Dataset dataset;
    @ManyToOne @JoinColumn (name = "user_id", nullable = false)
    private User user;
    @Column (nullable = false)
    private String fileName;
    @Column (nullable = false)
    private String filePath;
    @Enumerated (EnumType.STRING) @Column(nullable = false)
    private SubmissionStatus status;
    @Column (nullable = false)
    private boolean qaEnabled;
    @Column(nullable = false)
    private boolean isActive;
    @Column (nullable = false)
    private LocalDateTime createdAt;

    public Submission() {

    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
        this.isActive = true;
    }

    public String getId() {
        return id;
    }

    public Dataset getDataset() {
        return dataset;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }
}



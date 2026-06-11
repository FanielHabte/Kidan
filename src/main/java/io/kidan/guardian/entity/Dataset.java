package io.kidan.guardian.entity;

import io.kidan.guardian.enums.FileType;
import io.kidan.inlet.entity.Submission;
import io.kidan.nexus.entity.User;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (catalog = "kidan", schema = "guardian",
        indexes = {
        @Index(name = "idx_data_user", columnList = "user_id"),
        @Index(name = "idx_dataset_name", columnList = "name")
})

public class Dataset implements Serializable {
    @Serial
    private static final long serialVersionUID = 1298617547297121295L;
    @Id @GeneratedValue (strategy = GenerationType.UUID)
    private String id;
    @Column (nullable = false)
    private String name;
    @Enumerated (EnumType.STRING) @Column(nullable = false)
    private FileType fileType;
    @ManyToOne @JoinColumn (name = "user_id", nullable = false)
    private User user;
    @ManyToOne @JoinColumn (name = "updated_by", nullable = false)
    private User updatedBy;
    @Column (nullable = false)
    private boolean isActive;
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
    @Column (nullable = false)
    private LocalDateTime updatedAt;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dataset")
    private List<Submission> submissionList;

    public Dataset() {
    }

    @PrePersist
    private void setCreatedAt() {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.createdAt = localDateTime;
        this.updatedAt = localDateTime;
        this.isActive = true;
    }

    @PreUpdate
    private void updateEntity() {
        this.updatedAt = LocalDateTime.now();
    }

    public String getId () {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt () {
        return updatedAt;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Submission> getSubmissionList() {
        return submissionList;
    }

    public void setSubmissionList(List<Submission> submissionList) {
        this.submissionList = submissionList;
    }
}
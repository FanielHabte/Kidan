package guardian.entity;

import guardian.enums.FileType;
import nexus.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (catalog = "kidan", schema = "guardian",
        indexes = {
        @Index(name = "idx_data_user", columnList = "user_id"),
        @Index(name = "idx_dataset_name", columnList = "name")
})

public class Dataset {

    @Id @GeneratedValue (strategy = GenerationType.UUID)
    private String id;
    @Column (nullable = false)
    private String name;
    @Enumerated (EnumType.STRING) @Column(nullable = false)
    private FileType fileType;
    @ManyToOne @JoinColumn (name = "user_id", nullable = false)
    private User user;
    @Column (nullable = false)
    private boolean isActive;
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
    @Column (nullable = false)
    private LocalDateTime updatedAt;

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
    private void setUpdatedAt() {
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

}
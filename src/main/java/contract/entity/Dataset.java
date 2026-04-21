package contract.entity;

import contract.enums.FileType;
import gateway.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Dataset {

    @Id @GeneratedValue (strategy = GenerationType.UUID)
    private String id;
    private String name;
    @Enumerated
    private FileType fileType;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User userId;
    private LocalDateTime createdAt;
    public Dataset() {

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getId () {
        return id;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();

    }

}
package io.kidan.guardian.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (catalog = "kidan", schema = "guardian")
public class Contract {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne() @JoinColumn(name = "dataset_id", nullable = false)
    private Dataset dataset;
    @Column (nullable = false)
    private int version;
    @Column (nullable = false)
    private boolean isActive;
    @Column (updatable = false, nullable = false)
    private LocalDateTime createdAt;
    @Column (nullable = false)
    private LocalDateTime updatedAt;

    public Contract() {

    }

    @PrePersist
    private void setCreatedAt() {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.createdAt = localDateTime;
        this.updatedAt = localDateTime;
        this.isActive = true;
        this.version = 1;
    }

    @PreUpdate
    private void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
        this.version = version + 1;
    }

    public String getContractId() {
        return id;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    public Dataset getDataset() {
        return dataset;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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

package contract.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Contract {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne()
    @JoinColumn(name = "dataset_id")
    private Dataset datasetId;
    private int version;
    @Column (updatable = false)
    private LocalDateTime createdAt;

    public Contract() {

    }

    public String getContractId() {
        return id;
    }

    public Dataset getDatasetId() {
        return datasetId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();

    }

}

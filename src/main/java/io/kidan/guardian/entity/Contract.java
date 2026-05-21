package io.kidan.guardian.entity;

import io.kidan.nexus.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (catalog = "kidan", schema = "guardian")
public class Contract {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column (nullable = false)
    private String name;
    @OneToOne() @JoinColumn(name = "dataset_id", nullable = false)
    private Dataset dataset;
    @Column (nullable = false)
    private int version;
    @Column (nullable = false)
    private boolean isActive;
    // {delimiter:""}
    @Column(nullable = false)
    private String contractConfig;
    @OneToOne() @JoinColumn(name = "updated_by", nullable = false)
    private User updatedBy;
    @Column (updatable = false, nullable = false)
    private LocalDateTime createdAt;
    @Column (nullable = false)
    private LocalDateTime updatedAt;
    @Column (nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contract")
    private List<ContractRule> contractsRuleList;

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

    void setVersion(int version) {
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

    public List<ContractRule> getContractsRuleList() {
        return contractsRuleList;
    }

    public void setContractsRuleList(List<ContractRule> contractRules) {
        this.contractsRuleList = contractRules;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getContractConfig() {
        return contractConfig;
    }

    public void setContractConfig(String contractConfig) {
        this.contractConfig = contractConfig;
    }
}

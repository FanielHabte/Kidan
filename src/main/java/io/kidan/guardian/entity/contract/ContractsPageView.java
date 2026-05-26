package io.kidan.guardian.entity.contract;

import com.google.errorprone.annotations.Immutable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Immutable
@Table (schema = "guardian",name = "v_contracts_page_summary")
public class ContractsPageView implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "contract_id", nullable = false)
    private String contractId;
    @Column(name = "dataset_id", nullable = false)
    private String datasetId;
    @Column(name = "name", nullable = false)
    private String contractName;
    @Column(name = "dataset_name", nullable = false)
    private String datasetName;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "version", nullable = false)
    private int version;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "last_updated_by", nullable = false)
    private String lastUpdatedBy;
    @Column(name = "owner", nullable = false)
    private String owner;
    @Column(name = "rules_count", nullable = false)
    private int rulesCount;
    @Column(name = "active_contracts", nullable = false)
    private int activeContracts;
    @Column(name = "inactive_contracts", nullable = false)
    private int inActiveContracts;
    @Column(name = "total_contracts", nullable = false)
    private int totalContracts;
    @Column(name = "avg_rules", nullable = false)
    private double avgRules;

    public ContractsPageView() {
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getDatasetName() {
        return datasetName;
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getRulesCount() {
        return rulesCount;
    }

    public void setRulesCount(int rulesCount) {
        this.rulesCount = rulesCount;
    }

    public int getActiveContracts() {
        return activeContracts;
    }

    public void setActiveContracts(int activeContracts) {
        this.activeContracts = activeContracts;
    }

    public int getInActiveContracts() {
        return inActiveContracts;
    }

    public void setInActiveContracts(int inActiveContracts) {
        this.inActiveContracts = inActiveContracts;
    }

    public int getTotalContracts() {
        return totalContracts;
    }

    public void setTotalContracts(int totalContracts) {
        this.totalContracts = totalContracts;
    }

    public double getAvgRules() {
        return avgRules;
    }

    public void setAvgRules(double avgRules) {
        this.avgRules = avgRules;
    }
}

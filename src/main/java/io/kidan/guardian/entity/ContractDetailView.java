package io.kidan.guardian.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Immutable
@Table(schema = "guardian", name = "v_contract_detail_summary")
public class ContractDetailView implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private ContractRule contractRule;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    private Contract contract;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dataset_id")
    private Dataset dataset;
    @Column(name = "rules_count")
    private int rulesCount;
    @Column(name = "is_required_count")
    private int isRequiredCount;
    @Column(name = "is_unique_count")
    private int isUniqueCount;

    public ContractDetailView() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Dataset getDataset() {
        return dataset;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    public ContractRule getContractRule() {
        return contractRule;
    }

    public void setContractRule(ContractRule contractRule) {
        this.contractRule = contractRule;
    }

    public int getRulesCount() {
        return rulesCount;
    }

    public void setRulesCount(int rulesCount) {
        this.rulesCount = rulesCount;
    }

    public int getIsRequiredCount() {
        return isRequiredCount;
    }

    public void setIsRequiredCount(int isRequiredCount) {
        this.isRequiredCount = isRequiredCount;
    }

    public int getIsUniqueCount() {
        return isUniqueCount;
    }

    public void setIsUniqueCount(int isUniqueCount) {
        this.isUniqueCount = isUniqueCount;
    }
}

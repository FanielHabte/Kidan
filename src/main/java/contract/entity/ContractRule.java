package contract.entity;

import contract.enums.RuleType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ContractRule {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToOne
    @JoinColumn (name = "contract_id")
    private Contract contractId;
    private RuleType ruleType;
    private String ruleConfig;
    private boolean isRequired;
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public ContractRule() {

    }

    public String getId() {
        return id;
    }

    public Contract getContractId() {
        return contractId;
    }

    public RuleType getRuleType() {
        return ruleType;
    }

    public void setRuleType(RuleType ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleConfig() {
        return ruleConfig;
    }

    public void setRuleConfig(String ruleConfig) {
        this.ruleConfig = ruleConfig;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }
}



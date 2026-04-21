package validation.entity;

import contract.entity.ContractRule;
import jakarta.persistence.*;
import validation.enums.Result;

import java.time.LocalDateTime;

public class RuleResults {
    @Id @GeneratedValue (strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn (name = "pipeline_run_id")
    private PipelineRun pipelineRunId;
    @ManyToOne
    @JoinColumn (name = "contract_rule_id")

    private ContractRule contractRuleId;
    private Result result;
    private String detail;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    public RuleResults() {
    }

    public String getId() {
        return id;
    }

    public PipelineRun getPipelineRunId() {
        return pipelineRunId;
    }

    public void setPipelineRunId(PipelineRun pipelineRunId) {
        this.pipelineRunId = pipelineRunId;
    }

    public ContractRule getContractRuleId() {
        return contractRuleId;
    }

    public void setContractRuleId(ContractRule contractRuleId) {
        this.contractRuleId = contractRuleId;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

}

package verity.entity;

import guardian.entity.ContractRule;
import jakarta.persistence.*;
import spectra.entity.PipelineRun;
import verity.enums.Result;

import java.time.LocalDateTime;

@Entity
@Table (catalog = "kidan", schema = "verity",
        indexes = {
            @Index(name = "idx_result_pipeline_run", columnList = "pipeline_run_id"),
            @Index(name = "idx_result_contract_rule", columnList = "contract_rule_id"),
        }
)
public class RuleResult {
    @Id @GeneratedValue (strategy = GenerationType.UUID)
    private String id;
    @ManyToOne @JoinColumn (name = "pipeline_run_id", nullable = false)
    private PipelineRun pipelineRun;
    @ManyToOne @JoinColumn (name = "contract_rule_id", nullable = false)
    private ContractRule contractRule;
    @Enumerated (EnumType.STRING) @Column(nullable = false)
    private Result result;
    @Column(nullable = false)
    private String detail;
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    public RuleResult() {

    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public PipelineRun getPipelineRun() {
        return pipelineRun;
    }

    public void setPipelineRun(PipelineRun pipelineRun) {
        this.pipelineRun = pipelineRun;
    }

    public ContractRule getContractRule() {
        return contractRule;
    }

    public void setContractRule(ContractRule contractRule) {
        this.contractRule = contractRule;
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

}

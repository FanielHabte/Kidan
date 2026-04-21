package contract.entity;

import contract.enums.RuleType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ContractRule {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String ruleId;

    private String contractId;
    private RuleType ruleType;
    private String ruleConfig;
    private boolean isRequired;
    private LocalDateTime createdAt;

    public ContractRule() {

    }


}

//•	id (UUID, PK)
//•	contract_id (UUID, FK → contracts.id)
//•	rule_type (enum: PageCountRule, SchemaRule, KeywordRule, etc.)
//•	rule_config (jsonb) — Stores parameters like {"min_pages": 10}
//•	required (boolean) — true for blocking rules, false for warnings
//•	created_at (timestamp)

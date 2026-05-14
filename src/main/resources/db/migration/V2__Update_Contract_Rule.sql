ALTER TABLE guardian.contract_rule
    ADD is_unique BOOLEAN;

ALTER TABLE guardian.contract_rule
    ALTER COLUMN is_unique SET NOT NULL;

ALTER TABLE guardian.contract_rule
    ALTER COLUMN rule_config TYPE VARCHAR(255) USING (rule_config::VARCHAR(255));

ALTER TABLE guardian.contract_rule
    ALTER COLUMN rule_type TYPE VARCHAR(255) USING (rule_type::VARCHAR(255));
ALTER TABLE guardian.contract
    ADD contract_config VARCHAR(255);

ALTER TABLE guardian.contract
    ADD description VARCHAR(255);

ALTER TABLE guardian.contract
    ADD name VARCHAR(255);

ALTER TABLE guardian.contract
    ADD updated_by VARCHAR(255);

ALTER TABLE guardian.contract
    ALTER COLUMN contract_config SET NOT NULL;

ALTER TABLE guardian.contract
    ALTER COLUMN description SET NOT NULL;

ALTER TABLE guardian.contract
    ALTER COLUMN name SET NOT NULL;

ALTER TABLE guardian.contract
    ALTER COLUMN updated_by SET NOT NULL;

ALTER TABLE guardian.contract
    ADD CONSTRAINT uc_contract_dataset UNIQUE (dataset_id);

ALTER TABLE guardian.contract
    ADD CONSTRAINT uc_contract_updated_by UNIQUE (updated_by);

ALTER TABLE guardian.contract
    ADD CONSTRAINT FK_CONTRACT_ON_UPDATED_BY FOREIGN KEY (updated_by) REFERENCES nexus."user" (id);
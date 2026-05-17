CREATE TABLE guardian.csv_contract
(
    id            VARCHAR(255) NOT NULL,
    contract_id   VARCHAR(255) NOT NULL,
    contract_name VARCHAR(255) NOT NULL,
    column_names  VARCHAR(255) NOT NULL,
    description   VARCHAR(255) NOT NULL,
    CONSTRAINT pk_csvcontract PRIMARY KEY (id)
);

ALTER TABLE guardian.csv_contract
    ADD CONSTRAINT FK_CSVCONTRACT_ON_CONTRACT FOREIGN KEY (contract_id) REFERENCES guardian.contract (id);
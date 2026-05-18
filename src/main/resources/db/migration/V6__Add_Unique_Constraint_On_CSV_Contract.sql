ALTER TABLE guardian.csv_contract
    ADD CONSTRAINT uc_csvcontract_contract UNIQUE (contract_id);
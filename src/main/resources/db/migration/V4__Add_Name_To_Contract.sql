ALTER TABLE guardian.contract
    ADD name VARCHAR(255);

ALTER TABLE guardian.contract
    ALTER COLUMN name SET NOT NULL;
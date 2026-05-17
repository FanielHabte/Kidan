ALTER TABLE guardian.dataset
    ADD updated_by VARCHAR(255);

ALTER TABLE guardian.dataset
    ALTER COLUMN updated_by SET NOT NULL;

ALTER TABLE guardian.dataset
    ADD CONSTRAINT FK_DATASET_ON_UPDATED_BY FOREIGN KEY (updated_by) REFERENCES nexus."user" (id);

ALTER TABLE guardian.dataset
    ADD CONSTRAINT FK_DATASET_ON_USER FOREIGN KEY (user_id) REFERENCES nexus."user" (id);

ALTER TABLE guardian.dataset
    ALTER COLUMN file_type TYPE VARCHAR(255) USING (file_type::VARCHAR(255));
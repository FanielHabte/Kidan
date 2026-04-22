-- V1__initial_schema.sql

-- Create schemas
CREATE SCHEMA IF NOT EXISTS guardian;
CREATE SCHEMA IF NOT EXISTS inlet;
CREATE SCHEMA IF NOT EXISTS verity;
CREATE SCHEMA IF NOT EXISTS aether;
CREATE SCHEMA IF NOT EXISTS nexus;
CREATE SCHEMA IF NOT EXISTS audit;

-- Enable vector extension
CREATE EXTENSION IF NOT EXISTS vector;

-- Guardian schema tables
CREATE TABLE guardian.dataset (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    file_type VARCHAR(50) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_data_user ON guardian.dataset(user_id);
CREATE INDEX idx_dataset_name ON guardian.dataset(name);

CREATE TABLE guardian.contract (
    id VARCHAR(255) PRIMARY KEY,
    dataset_id VARCHAR(255) NOT NULL,
    version INTEGER NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (dataset_id) REFERENCES guardian.dataset(id)
);

CREATE TABLE guardian.contract_rule (
    id VARCHAR(255) PRIMARY KEY,
    contract_id VARCHAR(255) NOT NULL,
    rule_type VARCHAR(50) NOT NULL,
    rule_config TEXT NOT NULL,
    is_required BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (contract_id) REFERENCES guardian.contract(id)
);

-- Inlet schema tables
CREATE TABLE inlet.submission (
    id VARCHAR(255) PRIMARY KEY,
    dataset_id VARCHAR(255) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(500) NOT NULL,
    status VARCHAR(50) NOT NULL,
    qa_enabled BOOLEAN NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_submission_dataset ON inlet.submission(dataset_id);
CREATE INDEX idx_submission_user ON inlet.submission(user_id);

-- Verity schema tables
CREATE TABLE verity.pipeline_run (
    id VARCHAR(255) PRIMARY KEY,
    submission_id VARCHAR(255) NOT NULL,
    health_score INTEGER NOT NULL,
    passed_count INTEGER NOT NULL,
    failed_count INTEGER NOT NULL,
    warned_count INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE verity.rule_result (
    id VARCHAR(255) PRIMARY KEY,
    pipeline_run_id VARCHAR(255) NOT NULL,
    contract_rule_id VARCHAR(255) NOT NULL,
    result VARCHAR(50) NOT NULL,
    detail TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (pipeline_run_id) REFERENCES verity.pipeline_run(id),
    FOREIGN KEY (contract_rule_id) REFERENCES guardian.contract_rule(id)
);

CREATE INDEX idx_result_pipeline_run ON verity.rule_result(pipeline_run_id);
CREATE INDEX idx_result_contract_rule ON verity.rule_result(contract_rule_id);

-- Aether schema tables
CREATE TABLE aether.embedding (
    id VARCHAR(255) PRIMARY KEY,
    submission_id VARCHAR(255) NOT NULL,
    chunk_text TEXT NOT NULL,
    chunk_index INTEGER NOT NULL,
    embedding vector(1536) NOT NULL,
    meta_data TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_embedding_submission ON aether.embedding(submission_id);

-- Nexus schema tables
CREATE TABLE nexus.user (
    id VARCHAR(255) PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE nexus.resource_permission (
    id VARCHAR(255) PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    resource_type VARCHAR(50) NOT NULL,
    resource_id VARCHAR(255) NOT NULL,
    role_type VARCHAR(50) NOT NULL,
    granted_by VARCHAR(255) NOT NULL,
    granted_at TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES nexus.user(id),
    FOREIGN KEY (granted_by) REFERENCES nexus.user(id)
);

-- Audit schema tables
CREATE TABLE audit.audit_log (
    id VARCHAR(255) PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    resource_id VARCHAR(255) NOT NULL,
    action VARCHAR(50) NOT NULL,
    resource_type VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES nexus.user(id)
);
CREATE TABLE documentos_gerais_pdf (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    single_registry_registration BYTEA,
    marital_status BYTEA,
    identity_documents BYTEA,
    guardianship_documents BYTEA,
    vaccination_card BYTEA,
    proof_of_residence BYTEA,
    work_contract BYTEA,
    banking_relations_report BYTEA,
    proof_of_income BYTEA,
    supporting_documentation BYTEA,
    bank_statements BYTEA,
    business_documents BYTEA,
    tax_documents BYTEA,
    mei_documents BYTEA,
    health_disability BYTEA,
    family_composition BYTEA,
    government_program BYTEA,
    data_upload TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_user_documentos_gerais_pdf FOREIGN KEY (user_id) REFERENCES usuario(id)
);


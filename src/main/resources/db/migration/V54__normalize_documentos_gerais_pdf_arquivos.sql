CREATE TABLE documentos_gerais_pdf_arquivos (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    tipo_documento VARCHAR(80) NOT NULL,
    nome_arquivo VARCHAR(255) NOT NULL,
    mime_type VARCHAR(120) NOT NULL DEFAULT 'application/pdf',
    conteudo BYTEA NOT NULL,
    data_upload TIMESTAMP NOT NULL DEFAULT NOW(),
    status VARCHAR(50) NOT NULL DEFAULT 'ATIVO',
    CONSTRAINT fk_documentos_gerais_pdf_arquivos_user FOREIGN KEY (user_id) REFERENCES user_info(id)
);

CREATE INDEX idx_documentos_gerais_pdf_arquivos_user_tipo
    ON documentos_gerais_pdf_arquivos (user_id, tipo_documento);

INSERT INTO documentos_gerais_pdf_arquivos (user_id, tipo_documento, nome_arquivo, conteudo, data_upload, status)
SELECT user_id, 'singleRegistryRegistration', 'singleRegistryRegistration_' || id || '.pdf', single_registry_registration, data_upload, COALESCE(status, 'ATIVO')
FROM documentos_gerais_pdf WHERE single_registry_registration IS NOT NULL
UNION ALL
SELECT user_id, 'maritalStatus', 'maritalStatus_' || id || '.pdf', marital_status, data_upload, COALESCE(status, 'ATIVO')
FROM documentos_gerais_pdf WHERE marital_status IS NOT NULL
UNION ALL
SELECT user_id, 'identityDocuments', 'identityDocuments_' || id || '.pdf', identity_documents, data_upload, COALESCE(status, 'ATIVO')
FROM documentos_gerais_pdf WHERE identity_documents IS NOT NULL
UNION ALL
SELECT user_id, 'guardianshipDocuments', 'guardianshipDocuments_' || id || '.pdf', guardianship_documents, data_upload, COALESCE(status, 'ATIVO')
FROM documentos_gerais_pdf WHERE guardianship_documents IS NOT NULL
UNION ALL
SELECT user_id, 'vaccinationCard', 'vaccinationCard_' || id || '.pdf', vaccination_card, data_upload, COALESCE(status, 'ATIVO')
FROM documentos_gerais_pdf WHERE vaccination_card IS NOT NULL
UNION ALL
SELECT user_id, 'proofOfResidence', 'proofOfResidence_' || id || '.pdf', proof_of_residence, data_upload, COALESCE(status, 'ATIVO')
FROM documentos_gerais_pdf WHERE proof_of_residence IS NOT NULL
UNION ALL
SELECT user_id, 'workContract', 'workContract_' || id || '.pdf', work_contract, data_upload, COALESCE(status, 'ATIVO')
FROM documentos_gerais_pdf WHERE work_contract IS NOT NULL
UNION ALL
SELECT user_id, 'bankingRelationsReport', 'bankingRelationsReport_' || id || '.pdf', banking_relations_report, data_upload, COALESCE(status, 'ATIVO')
FROM documentos_gerais_pdf WHERE banking_relations_report IS NOT NULL
UNION ALL
SELECT user_id, 'proofOfIncome', 'proofOfIncome_' || id || '.pdf', proof_of_income, data_upload, COALESCE(status, 'ATIVO')
FROM documentos_gerais_pdf WHERE proof_of_income IS NOT NULL
UNION ALL
SELECT user_id, 'supportingDocumentation', 'supportingDocumentation_' || id || '.pdf', supporting_documentation, data_upload, COALESCE(status, 'ATIVO')
FROM documentos_gerais_pdf WHERE supporting_documentation IS NOT NULL
UNION ALL
SELECT user_id, 'bankStatements', 'bankStatements_' || id || '.pdf', bank_statements, data_upload, COALESCE(status, 'ATIVO')
FROM documentos_gerais_pdf WHERE bank_statements IS NOT NULL
UNION ALL
SELECT user_id, 'businessDocuments', 'businessDocuments_' || id || '.pdf', business_documents, data_upload, COALESCE(status, 'ATIVO')
FROM documentos_gerais_pdf WHERE business_documents IS NOT NULL
UNION ALL
SELECT user_id, 'taxDocuments', 'taxDocuments_' || id || '.pdf', tax_documents, data_upload, COALESCE(status, 'ATIVO')
FROM documentos_gerais_pdf WHERE tax_documents IS NOT NULL
UNION ALL
SELECT user_id, 'meiDocuments', 'meiDocuments_' || id || '.pdf', mei_documents, data_upload, COALESCE(status, 'ATIVO')
FROM documentos_gerais_pdf WHERE mei_documents IS NOT NULL
UNION ALL
SELECT user_id, 'healthDisability', 'healthDisability_' || id || '.pdf', health_disability, data_upload, COALESCE(status, 'ATIVO')
FROM documentos_gerais_pdf WHERE health_disability IS NOT NULL
UNION ALL
SELECT user_id, 'familyComposition', 'familyComposition_' || id || '.pdf', family_composition, data_upload, COALESCE(status, 'ATIVO')
FROM documentos_gerais_pdf WHERE family_composition IS NOT NULL
UNION ALL
SELECT user_id, 'governmentProgram', 'governmentProgram_' || id || '.pdf', government_program, data_upload, COALESCE(status, 'ATIVO')
FROM documentos_gerais_pdf WHERE government_program IS NOT NULL;

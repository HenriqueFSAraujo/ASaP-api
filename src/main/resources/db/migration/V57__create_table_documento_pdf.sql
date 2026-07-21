-- Substitui o modelo de "1 usuario : 1 linha com 17 colunas BYTEA (1 arquivo por tipo)"
-- de documentos_gerais_pdf por uma relacao real "1 usuario : N documentos por tipo".
-- A tabela documentos_gerais_pdf NAO e alterada nem apagada aqui - fica como arquivo
-- historico/rollback dos dados ja existentes.
CREATE TABLE documento_pdf (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    tipo_documento VARCHAR(50) NOT NULL,
    nome_arquivo VARCHAR(255),
    conteudo BYTEA NOT NULL,
    data_upload TIMESTAMP NOT NULL DEFAULT NOW(),
    status VARCHAR(50),
    CONSTRAINT fk_user_documento_pdf FOREIGN KEY (user_id) REFERENCES user_info(id)
);

CREATE INDEX idx_documento_pdf_user_tipo ON documento_pdf(user_id, tipo_documento);

-- Migra os dados hoje existentes nas 17 colunas de documentos_gerais_pdf: cada coluna
-- nao-nula vira 1 linha em documento_pdf. Como o modelo antigo so guardava 1 arquivo por
-- tipo (uploads repetidos sobrescreviam), isso preserva exatamente o que ja estava salvo,
-- sem inventar nem perder nada.
INSERT INTO documento_pdf (user_id, tipo_documento, nome_arquivo, conteudo, data_upload, status)
SELECT user_id, 'singleRegistryRegistration', 'singleRegistryRegistration.pdf', single_registry_registration, data_upload, status FROM documentos_gerais_pdf WHERE single_registry_registration IS NOT NULL
UNION ALL
SELECT user_id, 'maritalStatus', 'maritalStatus.pdf', marital_status, data_upload, status FROM documentos_gerais_pdf WHERE marital_status IS NOT NULL
UNION ALL
SELECT user_id, 'identityDocuments', 'identityDocuments.pdf', identity_documents, data_upload, status FROM documentos_gerais_pdf WHERE identity_documents IS NOT NULL
UNION ALL
SELECT user_id, 'guardianshipDocuments', 'guardianshipDocuments.pdf', guardianship_documents, data_upload, status FROM documentos_gerais_pdf WHERE guardianship_documents IS NOT NULL
UNION ALL
SELECT user_id, 'vaccinationCard', 'vaccinationCard.pdf', vaccination_card, data_upload, status FROM documentos_gerais_pdf WHERE vaccination_card IS NOT NULL
UNION ALL
SELECT user_id, 'proofOfResidence', 'proofOfResidence.pdf', proof_of_residence, data_upload, status FROM documentos_gerais_pdf WHERE proof_of_residence IS NOT NULL
UNION ALL
SELECT user_id, 'workContract', 'workContract.pdf', work_contract, data_upload, status FROM documentos_gerais_pdf WHERE work_contract IS NOT NULL
UNION ALL
SELECT user_id, 'bankingRelationsReport', 'bankingRelationsReport.pdf', banking_relations_report, data_upload, status FROM documentos_gerais_pdf WHERE banking_relations_report IS NOT NULL
UNION ALL
SELECT user_id, 'proofOfIncome', 'proofOfIncome.pdf', proof_of_income, data_upload, status FROM documentos_gerais_pdf WHERE proof_of_income IS NOT NULL
UNION ALL
SELECT user_id, 'supportingDocumentation', 'supportingDocumentation.pdf', supporting_documentation, data_upload, status FROM documentos_gerais_pdf WHERE supporting_documentation IS NOT NULL
UNION ALL
SELECT user_id, 'bankStatements', 'bankStatements.pdf', bank_statements, data_upload, status FROM documentos_gerais_pdf WHERE bank_statements IS NOT NULL
UNION ALL
SELECT user_id, 'businessDocuments', 'businessDocuments.pdf', business_documents, data_upload, status FROM documentos_gerais_pdf WHERE business_documents IS NOT NULL
UNION ALL
SELECT user_id, 'taxDocuments', 'taxDocuments.pdf', tax_documents, data_upload, status FROM documentos_gerais_pdf WHERE tax_documents IS NOT NULL
UNION ALL
SELECT user_id, 'meiDocuments', 'meiDocuments.pdf', mei_documents, data_upload, status FROM documentos_gerais_pdf WHERE mei_documents IS NOT NULL
UNION ALL
SELECT user_id, 'healthDisability', 'healthDisability.pdf', health_disability, data_upload, status FROM documentos_gerais_pdf WHERE health_disability IS NOT NULL
UNION ALL
SELECT user_id, 'familyComposition', 'familyComposition.pdf', family_composition, data_upload, status FROM documentos_gerais_pdf WHERE family_composition IS NOT NULL
UNION ALL
SELECT user_id, 'governmentProgram', 'governmentProgram.pdf', government_program, data_upload, status FROM documentos_gerais_pdf WHERE government_program IS NOT NULL;

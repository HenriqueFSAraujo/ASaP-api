-- Migration para garantir que todos os campos de arquivos estejam como BYTEA
ALTER TABLE documentos_gerais_pdf ALTER COLUMN single_registry_registration TYPE BYTEA USING single_registry_registration::bytea;
ALTER TABLE documentos_gerais_pdf ALTER COLUMN marital_status TYPE BYTEA USING marital_status::bytea;
ALTER TABLE documentos_gerais_pdf ALTER COLUMN identity_documents TYPE BYTEA USING identity_documents::bytea;
ALTER TABLE documentos_gerais_pdf ALTER COLUMN guardianship_documents TYPE BYTEA USING guardianship_documents::bytea;
ALTER TABLE documentos_gerais_pdf ALTER COLUMN vaccination_card TYPE BYTEA USING vaccination_card::bytea;
ALTER TABLE documentos_gerais_pdf ALTER COLUMN proof_of_residence TYPE BYTEA USING proof_of_residence::bytea;
ALTER TABLE documentos_gerais_pdf ALTER COLUMN work_contract TYPE BYTEA USING work_contract::bytea;
ALTER TABLE documentos_gerais_pdf ALTER COLUMN banking_relations_report TYPE BYTEA USING banking_relations_report::bytea;
ALTER TABLE documentos_gerais_pdf ALTER COLUMN proof_of_income TYPE BYTEA USING proof_of_income::bytea;
ALTER TABLE documentos_gerais_pdf ALTER COLUMN supporting_documentation TYPE BYTEA USING supporting_documentation::bytea;
ALTER TABLE documentos_gerais_pdf ALTER COLUMN bank_statements TYPE BYTEA USING bank_statements::bytea;
ALTER TABLE documentos_gerais_pdf ALTER COLUMN business_documents TYPE BYTEA USING business_documents::bytea;
ALTER TABLE documentos_gerais_pdf ALTER COLUMN tax_documents TYPE BYTEA USING tax_documents::bytea;
ALTER TABLE documentos_gerais_pdf ALTER COLUMN mei_documents TYPE BYTEA USING mei_documents::bytea;
ALTER TABLE documentos_gerais_pdf ALTER COLUMN health_disability TYPE BYTEA USING health_disability::bytea;
ALTER TABLE documentos_gerais_pdf ALTER COLUMN family_composition TYPE BYTEA USING family_composition::bytea;
ALTER TABLE documentos_gerais_pdf ALTER COLUMN government_program TYPE BYTEA USING government_program::bytea;


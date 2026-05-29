-- Tabela centralizada de status de validação por seção do formulário.
-- Cada usuário (aluno) tem uma linha por seção. Status: PENDING / APPROVED / REJECTED.
--
-- Separa o conceito de "dado preenchido" (status nas tabelas de dado) do conceito de
-- "validação do admin" (esta tabela). O admin pode aprovar/rejeitar cada seção
-- independentemente do estado do dado em si.
--
-- Seções (FormSectionEnum): SCHOLARSHIP_INFO, PERSONAL_DATA, PARENTS_DATA, ADDRESS_INFO,
-- FAMILY_COMPOSITION, REQUIRED_DOCUMENTS, PROPERTY_RELATIONS, CONSENT_TERMS.

CREATE TABLE IF NOT EXISTS form_validation_status (
    id BIGSERIAL PRIMARY KEY,
    user_info_id BIGINT NOT NULL,
    section VARCHAR(50) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    reviewer_id BIGINT,
    reviewed_at TIMESTAMP,
    comment TEXT,
    CONSTRAINT fk_fvs_user FOREIGN KEY (user_info_id) REFERENCES user_info(id) ON DELETE CASCADE,
    CONSTRAINT fk_fvs_reviewer FOREIGN KEY (reviewer_id) REFERENCES user_info(id) ON DELETE SET NULL,
    CONSTRAINT uq_fvs_user_section UNIQUE (user_info_id, section)
);

CREATE INDEX IF NOT EXISTS idx_fvs_user_info_id ON form_validation_status (user_info_id);

COMMENT ON TABLE form_validation_status IS
    'Status de validação do admin para cada seção do formulário de bolsa do aluno.';
COMMENT ON COLUMN form_validation_status.section IS
    'Seção do formulário: SCHOLARSHIP_INFO, PERSONAL_DATA, PARENTS_DATA, ADDRESS_INFO, FAMILY_COMPOSITION, REQUIRED_DOCUMENTS, PROPERTY_RELATIONS, CONSENT_TERMS';
COMMENT ON COLUMN form_validation_status.status IS
    'Status de validação: PENDING (default), APPROVED, REJECTED';

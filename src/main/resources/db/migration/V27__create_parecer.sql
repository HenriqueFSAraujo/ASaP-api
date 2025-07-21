CREATE TABLE parecer_socioeconomico (
    id BIGSERIAL PRIMARY KEY,

    nome_aluno VARCHAR(255),
    data_nascimento_aluno DATE,
    segmento_cursar_2025 VARCHAR(255),
    nome_responsavel VARCHAR(255),
    cpf_responsavel VARCHAR(20),
    telefone_responsavel VARCHAR(20),
    renda_bruta_familiar NUMERIC(12,2),
    quantidade_pessoas_familia INTEGER,
    renda_per_capita NUMERIC(12,2),
    renda_per_capita_salario_minimo NUMERIC(12,2),
    percentual_lc_187 DOUBLE PRECISION,
    beneficiario_programa_renda BOOLEAN,
    reside_proximo_unidade_escolar BOOLEAN,
    candidato_com_deficiencia BOOLEAN,
    doenca_grave_ou_deficiencia_familiar BOOLEAN,
    quantidade_menores_dezoito_anos INTEGER,
    aspectos_relevantes TEXT,
    resultado_socioeconomico VARCHAR(255),
    data_finalizacao_parecer DATE,

    user_id BIGINT NOT NULL,
    CONSTRAINT fk_parecer_user FOREIGN KEY (user_id) REFERENCES user_info(id)
);

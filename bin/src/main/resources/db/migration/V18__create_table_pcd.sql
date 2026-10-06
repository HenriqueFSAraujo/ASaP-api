CREATE TABLE pessoa_com_deficiencia (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    tipo_deficiencia VARCHAR(255),
    despesa_mensal NUMERIC(10,2),
    bens_posses_id BIGINT NOT NULL,
    CONSTRAINT fk_deficiencia_bens FOREIGN KEY (bens_posses_id) REFERENCES bens_posses(id) ON DELETE CASCADE
);
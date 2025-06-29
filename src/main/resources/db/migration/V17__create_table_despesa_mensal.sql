CREATE TABLE despesa_mensal (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255),
    valor NUMERIC(10,2),
    bens_posses_id BIGINT NOT NULL,
    CONSTRAINT fk_despesa_bens FOREIGN KEY (bens_posses_id) REFERENCES bens_posses(id) ON DELETE CASCADE
);
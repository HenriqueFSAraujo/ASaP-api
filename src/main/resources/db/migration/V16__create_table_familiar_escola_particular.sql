CREATE TABLE familiar_escola_particular (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    escola VARCHAR(255),
    valor_mensal NUMERIC(10,2),
    bens_posses_id BIGINT NOT NULL,
    CONSTRAINT fk_familiar_bens FOREIGN KEY (bens_posses_id) REFERENCES bens_posses(id) ON DELETE CASCADE
);
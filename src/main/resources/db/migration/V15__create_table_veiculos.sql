CREATE TABLE veiculos (
    id SERIAL PRIMARY KEY,
    marca_modelo VARCHAR(255),
    ano_fabricacao INT,
    utilizacao VARCHAR(255),
    bens_posses_id BIGINT NOT NULL,
    CONSTRAINT fk_veiculo_bens FOREIGN KEY (bens_posses_id) REFERENCES bens_posses(id) ON DELETE CASCADE
);
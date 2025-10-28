-- Migration V49: Cria a tabela familiar_escola_particular
CREATE TABLE familiar_escola_particular (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100),
    parentesco VARCHAR(50),
    escola VARCHAR(100),
    bens_posses_id BIGINT REFERENCES bens_posses(id) ON DELETE CASCADE
);


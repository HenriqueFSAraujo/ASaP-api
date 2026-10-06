CREATE TABLE escola (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cnpj VARCHAR(18) NOT NULL,
    nsu VARCHAR(50),
    endereco VARCHAR(255),
    tipo VARCHAR(20) NOT NULL,
    status VARCHAR(50),
    CONSTRAINT uk_escola_cnpj UNIQUE (cnpj)
);

COMMENT ON COLUMN escola.tipo IS 'PARTICULAR ou GRATUITA';

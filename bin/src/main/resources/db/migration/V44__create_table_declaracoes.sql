CREATE TABLE declaracoes (
    id BIGSERIAL PRIMARY KEY,
    nome_declarante VARCHAR(255) NOT NULL,
    rg_declarante VARCHAR(50) NOT NULL,
    cpf_declarante VARCHAR(50) NOT NULL,
    nome_aluno VARCHAR(255) NOT NULL,
    aceite_termos BOOLEAN NOT NULL
);

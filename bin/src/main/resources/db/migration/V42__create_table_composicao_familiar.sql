CREATE TABLE composicao_familiar (
    id BIGSERIAL PRIMARY KEY,
    nome_completo VARCHAR(255) NOT NULL,
    escolaridade VARCHAR(255),
    grau_parentesco VARCHAR(255),
    data_nascimento DATE,
    profissao_ativa VARCHAR(255),
    estado_civil VARCHAR(255),
    salario_bruto NUMERIC(19, 2)
);


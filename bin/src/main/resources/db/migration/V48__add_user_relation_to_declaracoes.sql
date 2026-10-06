-- Migration V48: Adiciona coluna user_id e chave estrangeira à tabela declaracoes
ALTER TABLE declaracoes
    ADD COLUMN user_id BIGINT;

ALTER TABLE declaracoes
    ADD CONSTRAINT fk_declaracoes_user FOREIGN KEY (user_id)
        REFERENCES user_info(id);


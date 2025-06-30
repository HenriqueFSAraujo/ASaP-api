-- Adiciona a coluna user_id na tabela processo_de_bolsa
ALTER TABLE processo_de_bolsa
ADD COLUMN user_id BIGINT;

-- Cria a foreign key referenciando userinfo(id)
ALTER TABLE processo_de_bolsa
ADD CONSTRAINT fk_processo_de_bolsa_user
FOREIGN KEY (user_id)
REFERENCES userinfo(id);

ALTER TABLE endereco_candidatos
ADD COLUMN user_id BIGINT NOT NULL;

ALTER TABLE endereco_candidatos
ADD CONSTRAINT fk_endereco_candidato_user
FOREIGN KEY (user_id)
REFERENCES user_info(id);
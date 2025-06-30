ALTER TABLE fomulario_dados_parentes
ADD COLUMN user_id BIGINT NOT NULL;

ALTER TABLE fomulario_dados_parentes
ADD CONSTRAINT fk_dados_parentes_user
FOREIGN KEY (user_id)
REFERENCES user_info(id);
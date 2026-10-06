ALTER TABLE fomulario_dados_pessoais
ADD COLUMN user_id BIGINT;

ALTER TABLE fomulario_dados_pessoais
ADD CONSTRAINT fk_form_dados_pessoais_user
FOREIGN KEY (user_id)
REFERENCES user_info(id);
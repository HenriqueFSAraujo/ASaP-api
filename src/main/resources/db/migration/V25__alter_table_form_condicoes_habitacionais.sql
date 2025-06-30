ALTER TABLE form_condicoes_habitacionais
ADD COLUMN user_id BIGINT NOT NULL;

ALTER TABLE form_condicoes_habitacionais
ADD CONSTRAINT fk_form_condicoes_habitacionais_user
FOREIGN KEY (user_id)
REFERENCES userinfo(id);
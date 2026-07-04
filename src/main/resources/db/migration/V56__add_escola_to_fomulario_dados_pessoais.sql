ALTER TABLE fomulario_dados_pessoais
    ADD COLUMN IF NOT EXISTS escola_id BIGINT;

ALTER TABLE fomulario_dados_pessoais
    ADD CONSTRAINT fk_form_dados_pessoais_escola FOREIGN KEY (escola_id) REFERENCES escola(id);

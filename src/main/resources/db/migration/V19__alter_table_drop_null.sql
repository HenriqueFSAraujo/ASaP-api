ALTER TABLE processo_de_bolsa ALTER COLUMN percentual DROP NOT NULL;
ALTER TABLE processo_de_bolsa ADD COLUMN user_info_id BIGINT;
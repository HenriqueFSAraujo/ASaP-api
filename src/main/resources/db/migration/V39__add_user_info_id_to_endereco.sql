ALTER TABLE endereco ADD COLUMN user_info_id BIGINT;
ALTER TABLE endereco ADD CONSTRAINT fk_endereco_user_info FOREIGN KEY (user_info_id) REFERENCES user_info(id);


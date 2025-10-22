ALTER TABLE composicao_familiar
ADD COLUMN user_info_id BIGINT NOT NULL,
ADD CONSTRAINT fk_user_info FOREIGN KEY (user_info_id) REFERENCES user_info (id);


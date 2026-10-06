CREATE TABLE bens_posses (
    id SERIAL PRIMARY KEY,
    user_info_id BIGINT NOT NULL,
    CONSTRAINT fk_bens_user FOREIGN KEY (user_info_id) REFERENCES user_info(id)
);

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM information_schema.tables
        WHERE table_name = 'familiar_escola_particular'
    ) THEN
        CREATE TABLE familiar_escola_particular (
            id BIGSERIAL PRIMARY KEY,
            nome VARCHAR(255),
            escola VARCHAR(255),
            valor_mensal NUMERIC(19,2),
            status VARCHAR(50),
            bens_posses_id BIGINT,
            CONSTRAINT fk_familiar_bens FOREIGN KEY (bens_posses_id) REFERENCES bens_posses(id) ON DELETE CASCADE
        );
    END IF;
END $$;


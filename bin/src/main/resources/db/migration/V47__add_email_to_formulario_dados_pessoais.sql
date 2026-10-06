-- Migration V47: Adiciona o campo email à tabela fomulario_dados_pessoais
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM information_schema.columns
        WHERE table_name='fomulario_dados_pessoais' AND column_name='email'
    ) THEN
        ALTER TABLE fomulario_dados_pessoais ADD COLUMN email VARCHAR(100);
    END IF;
END $$;

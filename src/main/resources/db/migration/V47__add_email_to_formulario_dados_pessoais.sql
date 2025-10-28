-- Migration V47: Adiciona o campo email à tabela fomulario_dados_pessoais
ALTER TABLE fomulario_dados_pessoais
    ADD COLUMN email VARCHAR(100);


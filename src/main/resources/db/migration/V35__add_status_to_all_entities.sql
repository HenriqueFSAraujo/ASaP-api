-- Migration para adicionar a coluna 'status' em todas as entidades
ALTER TABLE agenda ADD COLUMN status VARCHAR(255);
ALTER TABLE bens_posse ADD COLUMN status VARCHAR(255);
ALTER TABLE despesa_mensal ADD COLUMN status VARCHAR(255);
ALTER TABLE documentos_gerais ADD COLUMN status VARCHAR(255);
ALTER TABLE documentos_gerais_pdf ADD COLUMN status VARCHAR(255);
ALTER TABLE endereco ADD COLUMN status VARCHAR(255);
ALTER TABLE familiar_escola_particular ADD COLUMN status VARCHAR(255);
ALTER TABLE form_condicoes_habitacionais ADD COLUMN status VARCHAR(255);
ALTER TABLE fomulario_dados_parentes ADD COLUMN status VARCHAR(255);
ALTER TABLE fomulario_dados_pessoais ADD COLUMN status VARCHAR(255);
ALTER TABLE endereco_candidatos ADD COLUMN status VARCHAR(255);
ALTER TABLE paciente ADD COLUMN status VARCHAR(255);
ALTER TABLE parecer_socioeconomico ADD COLUMN status VARCHAR(255);
ALTER TABLE pessoa_com_deficiencia ADD COLUMN status VARCHAR(255);
ALTER TABLE processo_de_bolsa ADD COLUMN status VARCHAR(255);
ALTER TABLE roles ADD COLUMN status VARCHAR(255);
ALTER TABLE user_info ADD COLUMN status VARCHAR(255);
ALTER TABLE usuario ADD COLUMN status VARCHAR(255);
ALTER TABLE veiculos ADD COLUMN status VARCHAR(255);


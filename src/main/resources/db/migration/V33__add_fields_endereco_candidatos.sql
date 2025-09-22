-- Ajusta a tabela endereco_candidatos para o novo modelo

-- Remover colunas antigas que não fazem parte do novo JSON
ALTER TABLE endereco_candidatos
    DROP COLUMN IF EXISTS transporte_educacional,
    DROP COLUMN IF EXISTS telefone_residencial,
    DROP COLUMN IF EXISTS telefone_trabalho,
    DROP COLUMN IF EXISTS telefone_celular,
    DROP COLUMN IF EXISTS email_confirmacao,
    DROP COLUMN IF EXISTS responsavel_legal,
    DROP COLUMN IF EXISTS segmento_2025;

-- Adicionar colunas novas apenas se não existirem
ALTER TABLE endereco_candidatos
    ADD COLUMN IF NOT EXISTS descricao_atividade VARCHAR(255),
    ADD COLUMN IF NOT EXISTS address VARCHAR(255),
    ADD COLUMN IF NOT EXISTS afterSchoolActivities VARCHAR(255),
    ADD COLUMN IF NOT EXISTS city VARCHAR(255),
    ADD COLUMN IF NOT EXISTS commutingTime VARCHAR(255),
    ADD COLUMN IF NOT EXISTS electricitySource VARCHAR(255),
    ADD COLUMN IF NOT EXISTS hasSewage VARCHAR(255),
    ADD COLUMN IF NOT EXISTS neighborhood VARCHAR(255),
    ADD COLUMN IF NOT EXISTS referencePoint VARCHAR(255),
    ADD COLUMN IF NOT EXISTS residenceType VARCHAR(255),
    ADD COLUMN IF NOT EXISTS structureType VARCHAR(255),
    ADD COLUMN IF NOT EXISTS structureTypeOthers VARCHAR(255),
    ADD COLUMN IF NOT EXISTS transportType VARCHAR(255),
    ADD COLUMN IF NOT EXISTS transportTypeOthers VARCHAR(255),
    ADD COLUMN IF NOT EXISTS waterSupply VARCHAR(255),
    ADD COLUMN IF NOT EXISTS weeklyFrequency VARCHAR(255),
    ADD COLUMN IF NOT EXISTS zipCode VARCHAR(255);

-- Renomear colunas se necessário
-- Exemplo:
-- ALTER TABLE endereco_candidatos RENAME COLUMN atividades_contraturno TO afterSchoolActivities;
-- ALTER TABLE endereco_candidatos RENAME COLUMN endereco TO address;

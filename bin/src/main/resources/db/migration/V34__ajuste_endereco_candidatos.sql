-- Ajusta a tabela endereco_candidatos para manter apenas os campos do novo JSON
ALTER TABLE endereco_candidatos
    DROP COLUMN IF EXISTS transporte_educacional,
    DROP COLUMN IF EXISTS atividades_contraturno_old,
    DROP COLUMN IF EXISTS tempo_deslocamento_old,
    DROP COLUMN IF EXISTS telefone_residencial,
    DROP COLUMN IF EXISTS telefone_trabalho,
    DROP COLUMN IF EXISTS telefone_celular,
    DROP COLUMN IF EXISTS email_confirmacao,
    DROP COLUMN IF EXISTS responsavel_legal,
    DROP COLUMN IF EXISTS segmento_2025;

-- Renomeia colunas se necessário (exemplo)
-- ALTER TABLE endereco_candidatos RENAME COLUMN atividades_contraturno TO afterSchoolActivities;
-- ALTER TABLE endereco_candidatos RENAME COLUMN endereco TO address;
-- ALTER TABLE endereco_candidatos RENAME COLUMN cidade TO city;
-- ALTER TABLE endereco_candidatos RENAME COLUMN tempo_deslocamento TO commutingTime;
-- ALTER TABLE endereco_candidatos RENAME COLUMN fonte_energia TO electricitySource;
-- ALTER TABLE endereco_candidatos RENAME COLUMN tem_esgoto TO hasSewage;
-- ALTER TABLE endereco_candidatos RENAME COLUMN bairro TO neighborhood;
-- ALTER TABLE endereco_candidatos RENAME COLUMN ponto_referencia TO referencePoint;
-- ALTER TABLE endereco_candidatos RENAME COLUMN residencia TO residenceType;
-- ALTER TABLE endereco_candidatos RENAME COLUMN tipo_estrutura TO structureType;
-- ALTER TABLE endereco_candidatos RENAME COLUMN tipo_estrutura_outros TO structureTypeOthers;
-- ALTER TABLE endereco_candidatos RENAME COLUMN tipo_transporte TO transportType;
-- ALTER TABLE endereco_candidatos RENAME COLUMN tipo_transporte_outros TO transportTypeOthers;
-- ALTER TABLE endereco_candidatos RENAME COLUMN abastecimento_agua TO waterSupply;
-- ALTER TABLE endereco_candidatos RENAME COLUMN frequencia_semanal TO weeklyFrequency;
-- ALTER TABLE endereco_candidatos RENAME COLUMN cep TO zipCode;

-- Se algum campo não existir, adicione:
-- ALTER TABLE endereco_candidatos ADD COLUMN ... VARCHAR(255);

-- Os campos que devem permanecer já estão presentes conforme a última alteração.


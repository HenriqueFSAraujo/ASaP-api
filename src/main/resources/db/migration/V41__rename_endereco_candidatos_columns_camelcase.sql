-- Migration V41: Renomeia todas as colunas da tabela endereco_candidatos para camelCase conforme a entidade
ALTER TABLE endereco_candidatos RENAME COLUMN descricao_atividade TO activityDescription;
ALTER TABLE endereco_candidatos RENAME COLUMN endereco TO address;
ALTER TABLE endereco_candidatos RENAME COLUMN atividades_contraturno TO afterSchoolActivities;
ALTER TABLE endereco_candidatos RENAME COLUMN cidade TO city;
ALTER TABLE endereco_candidatos RENAME COLUMN tempo_deslocamento TO commutingTime;
ALTER TABLE endereco_candidatos RENAME COLUMN fonte_energia TO electricitySource;
ALTER TABLE endereco_candidatos RENAME COLUMN tem_esgoto TO hasSewage;
ALTER TABLE endereco_candidatos RENAME COLUMN bairro TO neighborhood;
ALTER TABLE endereco_candidatos RENAME COLUMN ponto_referencia TO referencePoint;
ALTER TABLE endereco_candidatos RENAME COLUMN residencia TO residenceType;
ALTER TABLE endereco_candidatos RENAME COLUMN tipo_estrutura TO structureType;
ALTER TABLE endereco_candidatos RENAME COLUMN tipo_estrutura_outros TO structureTypeOthers;
ALTER TABLE endereco_candidatos RENAME COLUMN tipo_transporte TO transportType;
ALTER TABLE endereco_candidatos RENAME COLUMN tipo_transporte_outros TO transportTypeOthers;
ALTER TABLE endereco_candidatos RENAME COLUMN abastecimento_agua TO waterSupply;
ALTER TABLE endereco_candidatos RENAME COLUMN frequencia_semanal TO weeklyFrequency;
ALTER TABLE endereco_candidatos RENAME COLUMN cep TO zipCode;



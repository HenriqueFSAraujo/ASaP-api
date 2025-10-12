-- Migration V41: Renomeia colunas para camelCase apenas se ainda não existem
DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='activityDescription') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN descricao_atividade TO activityDescription;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='address') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN endereco TO address;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='afterSchoolActivities') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN atividades_contraturno TO afterSchoolActivities;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='city') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN cidade TO city;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='commutingTime') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN tempo_deslocamento TO commutingTime;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='electricitySource') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN fonte_energia TO electricitySource;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='hasSewage') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN tem_esgoto TO hasSewage;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='neighborhood') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN bairro TO neighborhood;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='referencePoint') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN ponto_referencia TO referencePoint;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='residenceType') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN residencia TO residenceType;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='structureType') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN tipo_estrutura TO structureType;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='structureTypeOthers') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN tipo_estrutura_outros TO structureTypeOthers;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='transportType') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN tipo_transporte TO transportType;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='transportTypeOthers') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN tipo_transporte_outros TO transportTypeOthers;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='waterSupply') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN abastecimento_agua TO waterSupply;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='weeklyFrequency') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN frequencia_semanal TO weeklyFrequency;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='zipCode') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN cep TO zipCode;
    END IF;
END $$;
-- status e user_id já estão corretos


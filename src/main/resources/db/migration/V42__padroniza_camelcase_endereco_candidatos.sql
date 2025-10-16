DO $$ BEGIN
    -- activityDescription
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='descricao_atividade') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN descricao_atividade TO activityDescription;
    END IF;
    -- address
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='endereco') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN endereco TO address;
    END IF;
    -- afterSchoolActivities
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='atividades_contraturno') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN atividades_contraturno TO afterSchoolActivities;
    END IF;
    -- city
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='cidade') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN cidade TO city;
    END IF;
    -- commutingTime
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='tempo_deslocamento') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN tempo_deslocamento TO commutingTime;
    END IF;
    -- electricitySource
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='fonte_energia') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN fonte_energia TO electricitySource;
    END IF;
    -- hasSewage
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='tem_esgoto') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN tem_esgoto TO hasSewage;
    END IF;
    -- neighborhood
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='bairro') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN bairro TO neighborhood;
    END IF;
    -- referencePoint
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='ponto_referencia') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN ponto_referencia TO referencePoint;
    END IF;
    -- residenceType
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='residencia') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN residencia TO residenceType;
    END IF;
    -- structureType
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='tipo_estrutura') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN tipo_estrutura TO structureType;
    END IF;
    -- structureTypeOthers
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='tipo_estrutura_outros') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN tipo_estrutura_outros TO structureTypeOthers;
    END IF;
    -- transportType
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='tipo_transporte') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN tipo_transporte TO transportType;
    END IF;
    -- transportTypeOthers
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='tipo_transporte_outros') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN tipo_transporte_outros TO transportTypeOthers;
    END IF;
    -- waterSupply
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='abastecimento_agua') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN abastecimento_agua TO waterSupply;
    END IF;
    -- weeklyFrequency
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='frequencia_semanal') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN frequencia_semanal TO weeklyFrequency;
    END IF;
    -- zipCode
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='cep') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN cep TO zipCode;
    END IF;
END $$;
-- status e user_id já estão corretos

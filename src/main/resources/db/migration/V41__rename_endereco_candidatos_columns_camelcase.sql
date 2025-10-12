DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='activitydescription') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN descricao_atividade TO activityDescription;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='address') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN endereco TO address;
    END IF;
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='afterschoolactivities')
       AND NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='afterschoolactivities') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN afterschoolactivities TO afterSchoolActivities;
    ELSIF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='afterschoolactivities')
       AND NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='afterschoolactivities') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN atividades_contraturno TO afterSchoolActivities;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='city') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN cidade TO city;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='commutingtime') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN tempo_deslocamento TO commutingTime;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='electricitysource') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN fonte_energia TO electricitySource;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='hassewage') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN tem_esgoto TO hasSewage;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='neighborhood') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN bairro TO neighborhood;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='referencepoint') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN ponto_referencia TO referencePoint;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='residencetype') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN residencia TO residenceType;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='structuretype') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN tipo_estrutura TO structureType;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='structuretypeothers') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN tipo_estrutura_outros TO structureTypeOthers;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='transporttype') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN tipo_transporte TO transportType;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='transporttypeothers') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN tipo_transporte_outros TO transportTypeOthers;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='watersupply') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN abastecimento_agua TO waterSupply;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='weeklyfrequency') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN frequencia_semanal TO weeklyFrequency;
    END IF;
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='zipcode') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN cep TO zipCode;
    END IF;
END $$;
-- status e user_id já estão corretos

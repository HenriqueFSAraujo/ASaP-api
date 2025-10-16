DO $$ BEGIN
    -- activityDescription
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='activitydescription') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN activitydescription TO activityDescription;
    END IF;
    -- address
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='address') AND NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='address') THEN
        -- already correct
    END IF;
    -- afterSchoolActivities
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='afterschoolactivities') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN afterschoolactivities TO afterSchoolActivities;
    END IF;
    -- city
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='city') AND NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='city') THEN
        -- already correct
    END IF;
    -- commutingTime
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='commutingtime') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN commutingtime TO commutingTime;
    END IF;
    -- electricitySource
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='electricitysource') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN electricitysource TO electricitySource;
    END IF;
    -- hasSewage
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='hassewage') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN hassewage TO hasSewage;
    END IF;
    -- neighborhood
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='neighborhood') AND NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='neighborhood') THEN
        -- already correct
    END IF;
    -- referencePoint
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='referencepoint') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN referencepoint TO referencePoint;
    END IF;
    -- residenceType
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='residencetype') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN residencetype TO residenceType;
    END IF;
    -- structureType
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='structuretype') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN structuretype TO structureType;
    END IF;
    -- structureTypeOthers
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='structuretypeothers') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN structuretypeothers TO structureTypeOthers;
    END IF;
    -- transportType
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='transporttype') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN transporttype TO transportType;
    END IF;
    -- transportTypeOthers
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='transporttypeothers') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN transporttypeothers TO transportTypeOthers;
    END IF;
    -- waterSupply
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='watersupply') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN watersupply TO waterSupply;
    END IF;
    -- weeklyFrequency
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='weeklyfrequency') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN weeklyfrequency TO weeklyFrequency;
    END IF;
    -- zipCode
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='endereco_candidatos' AND column_name='zipcode') THEN
        ALTER TABLE endereco_candidatos RENAME COLUMN zipcode TO zipCode;
    END IF;
END $$;
-- status e user_id já estão corretos


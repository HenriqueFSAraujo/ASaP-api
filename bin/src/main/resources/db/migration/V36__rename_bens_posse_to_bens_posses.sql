DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'bens_posse') THEN
        EXECUTE 'ALTER TABLE bens_posse RENAME TO bens_posses';
    END IF;
END $$;


ALTER TABLE processo_de_bolsa
ALTER COLUMN percentual TYPE VARCHAR
USING percentual::VARCHAR;
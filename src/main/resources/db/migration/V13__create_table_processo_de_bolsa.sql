CREATE TABLE processo_de_bolsa (
    id BIGSERIAL PRIMARY KEY,
    vai_participar BOOLEAN NOT NULL,
    ja_foi_contemplado BOOLEAN NOT NULL,
    percentual INT NOT NULL
);
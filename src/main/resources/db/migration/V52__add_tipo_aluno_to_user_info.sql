-- Adiciona coluna tipo_aluno em user_info para classificar candidatos.
-- Valores aceitos: ESCOLA_PARTICULAR, ESCOLA_GRATUITA.
-- Permanece NULL para usuarios com perfil ROLE_ADMIN e para os 194 alunos ja cadastrados antes desta migration.

ALTER TABLE user_info
    ADD COLUMN IF NOT EXISTS tipo_aluno VARCHAR(30);

COMMENT ON COLUMN user_info.tipo_aluno IS
    'Classificacao do aluno: ESCOLA_PARTICULAR ou ESCOLA_GRATUITA. NULL para admin ou nao classificado.';

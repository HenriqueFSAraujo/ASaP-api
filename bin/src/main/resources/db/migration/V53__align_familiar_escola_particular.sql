-- Alinha a tabela familiar_escola_particular com a entidade JPA FamiliarEscolaParticular.
--
-- Contexto: durante o historico da V16/V49/V51 (tres tentativas de criar a tabela) e da edicao
-- retroativa de migrations anteriores mascarada pelo FlywayRepairRunner, o schema em producao
-- ficou com colunas (nome, parentesco, escola, bens_posses_id) - mas a entidade Java espera
-- (nome, escola, valor_mensal, status, bens_posses_id).
--
-- Esta migration adiciona as colunas faltantes para que o fluxo de bens/familiares passe a
-- funcionar. A coluna 'parentesco' e mantida como legado nullable (sem uso pelo codigo) - sera
-- removida em uma migration futura apos confirmar que nada externo depende dela.

ALTER TABLE familiar_escola_particular
    ADD COLUMN IF NOT EXISTS valor_mensal NUMERIC(19, 2);

ALTER TABLE familiar_escola_particular
    ADD COLUMN IF NOT EXISTS status VARCHAR(50) DEFAULT 'PENDENTE';

-- Adiciona novos campos para o formulário de endereço do candidato
ALTER TABLE endereco_candidatos
    ADD COLUMN descricao_atividade VARCHAR(255),
    ADD COLUMN atividades_contraturno VARCHAR(255),
    ADD COLUMN fonte_energia VARCHAR(255),
    ADD COLUMN tem_esgoto VARCHAR(255),
    ADD COLUMN tipo_estrutura VARCHAR(255),
    ADD COLUMN tipo_estrutura_outros VARCHAR(255),
    ADD COLUMN tipo_transporte VARCHAR(255),
    ADD COLUMN tipo_transporte_outros VARCHAR(255),
    ADD COLUMN abastecimento_agua VARCHAR(255),
    ADD COLUMN frequencia_semanal VARCHAR(255);


package pdev.com.agenda.domain.enuns;

/**
 * Seções do formulário de bolsa do aluno, usadas para validação pelo admin.
 * <p>
 * As 8 seções correspondem às tabs do {@code FormValidation} no front-end.
 */
public enum FormSectionEnum {

    /** Processo de Bolsa de Estudo — {@code processo_de_bolsa}. */
    SCHOLARSHIP_INFO,

    /** Dados Pessoais — {@code fomulario_dados_pessoais}. */
    PERSONAL_DATA,

    /** Dados dos Pais — {@code fomulario_dados_parentes}. */
    PARENTS_DATA,

    /** Endereço — {@code endereco_candidatos}. */
    ADDRESS_INFO,

    /** Composição Familiar — {@code composicao_familiar}. */
    FAMILY_COMPOSITION,

    /** Documentos Necessários — {@code documentos_gerais_pdf}. */
    REQUIRED_DOCUMENTS,

    /** Bens e Posses — {@code bens_posses}. */
    PROPERTY_RELATIONS,

    /** Termos de Consentimento — {@code declaracoes}. */
    CONSENT_TERMS
}

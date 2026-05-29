package pdev.com.agenda.domain.enuns;

/**
 * Status de validação do admin para uma seção do formulário do aluno.
 */
public enum ValidationStatusEnum {

    /** Pendente — admin ainda não avaliou esta seção. Default ao inicializar. */
    PENDING,

    /** Aprovada — admin validou e aprovou os dados desta seção. */
    APPROVED,

    /** Rejeitada — admin marcou esta seção como precisando ajuste do aluno. */
    REJECTED
}

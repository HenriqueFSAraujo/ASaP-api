package pdev.com.agenda.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pdev.com.agenda.domain.enuns.FormSectionEnum;
import pdev.com.agenda.domain.enuns.ValidationStatusEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDateTime;

/**
 * Status de validação do admin para uma seção do formulário do aluno.
 * <p>
 * Cada aluno tem no máximo 1 linha por seção (constraint UNIQUE em user_info_id + section).
 * Quando a seção ainda não foi avaliada, o registro pode não existir — o service trata isso
 * inicializando como {@link ValidationStatusEnum#PENDING}.
 */
@Entity
@Table(
        name = "form_validation_status",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_fvs_user_section",
                columnNames = {"user_info_id", "section"}
        )
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormValidationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_info_id", nullable = false)
    private UserInfo userInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "section", nullable = false, length = 50)
    private FormSectionEnum section;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ValidationStatusEnum status;

    /** Admin que fez a última alteração (nullable enquanto o status for PENDING). */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id")
    private UserInfo reviewer;

    @Column(name = "reviewed_at")
    private LocalDateTime reviewedAt;

    /** Comentário opcional do admin (ex: motivo da rejeição). */
    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;
}

package pdev.com.agenda.domain.dto;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import pdev.com.agenda.domain.dto.validation.FormEnderecoCandidatoValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = FormEnderecoCandidatoValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFormEnderecoCandidato {

    String message() default "Dados condicionais do endereço inválidos.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

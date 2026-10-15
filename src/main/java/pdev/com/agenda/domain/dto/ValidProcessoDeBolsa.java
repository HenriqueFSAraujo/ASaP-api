package pdev.com.agenda.domain.dto;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import pdev.com.agenda.domain.dto.validation.ProcessoDeBolsaValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = ProcessoDeBolsaValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidProcessoDeBolsa {

    String message() default "Percentual de bolsa anterior inválido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

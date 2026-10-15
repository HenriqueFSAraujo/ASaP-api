package pdev.com.agenda.domain.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pdev.com.agenda.domain.dto.ProcessoDeBolsaDTO;
import pdev.com.agenda.domain.dto.ValidProcessoDeBolsa;

public class ProcessoDeBolsaValidator
        implements ConstraintValidator<ValidProcessoDeBolsa, ProcessoDeBolsaDTO> {

    @Override
    public boolean isValid(ProcessoDeBolsaDTO dto, ConstraintValidatorContext context) {
        if (dto == null) {
            return true;
        }

        boolean valid = Boolean.TRUE.equals(dto.jaFoiContemplado())
                ? "50".equals(dto.percentual()) || "100".equals(dto.percentual())
                : dto.percentual() == null || "0".equals(dto.percentual())
                || "50".equals(dto.percentual()) || "100".equals(dto.percentual());
        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "Informe 50% ou 100% quando houve contemplação anterior.")
                    .addPropertyNode("percentual")
                    .addConstraintViolation();
        }
        return valid;
    }
}

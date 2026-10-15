package pdev.com.agenda.domain.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pdev.com.agenda.domain.dto.FormEnderecoCandidatoDTO;
import pdev.com.agenda.domain.dto.ValidFormEnderecoCandidato;

public class FormEnderecoCandidatoValidator
        implements ConstraintValidator<ValidFormEnderecoCandidato, FormEnderecoCandidatoDTO> {

    @Override
    public boolean isValid(FormEnderecoCandidatoDTO dto, ConstraintValidatorContext context) {
        if (dto == null) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        boolean valid = true;

        if ("Sim".equals(dto.afterSchoolActivities())) {
            valid &= require(context, dto.activityDescription(), "activityDescription");
            valid &= require(context, dto.weeklyFrequency(), "weeklyFrequency");
        }
        if ("outros".equals(dto.transportType())) {
            valid &= require(context, dto.transportTypeOthers(), "transportTypeOthers");
        }
        if ("Outros".equals(dto.structureType())) {
            valid &= require(context, dto.structureTypeOthers(), "structureTypeOthers");
        }

        return valid;
    }

    private boolean require(ConstraintValidatorContext context, String value, String field) {
        if (value != null && !value.isBlank()) {
            return true;
        }
        context.buildConstraintViolationWithTemplate("Campo obrigatório para a opção selecionada.")
                .addPropertyNode(field)
                .addConstraintViolation();
        return false;
    }
}

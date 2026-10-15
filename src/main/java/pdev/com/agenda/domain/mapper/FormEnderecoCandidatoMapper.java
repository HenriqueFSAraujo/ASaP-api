package pdev.com.agenda.domain.mapper;

import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.dto.FormEnderecoCandidatoDTO;
import pdev.com.agenda.domain.entity.FormEnderecoCandidato;
import pdev.com.agenda.domain.entity.UserInfo;

@Component
public class FormEnderecoCandidatoMapper {

    public FormEnderecoCandidato toEntity(FormEnderecoCandidatoDTO dto) {
        if (dto == null) {
            return null;
        }
        FormEnderecoCandidato entity = new FormEnderecoCandidato();
        entity.setActivityDescription(dto.activityDescription());
        entity.setAddress(dto.address());
        entity.setAfterSchoolActivities(dto.afterSchoolActivities());
        entity.setCity(dto.city());
        entity.setCommutingTime(dto.commutingTime());
        entity.setElectricitySource(dto.electricitySource());
        entity.setHasSewage(dto.hasSewage());
        entity.setNeighborhood(dto.neighborhood());
        entity.setReferencePoint(dto.referencePoint());
        entity.setResidenceType(dto.residenceType());
        entity.setStructureType(dto.structureType());
        entity.setStructureTypeOthers(dto.structureTypeOthers());
        entity.setTransportType(dto.transportType());
        entity.setTransportTypeOthers(dto.transportTypeOthers());
        entity.setWaterSupply(dto.waterSupply());
        entity.setWeeklyFrequency(dto.weeklyFrequency());
        entity.setZipCode(dto.zipCode());
        UserInfo user = new UserInfo();
        user.setId(dto.userId());
        entity.setUser(user);
        return entity;
    }

    public FormEnderecoCandidatoDTO toDto(FormEnderecoCandidato entity) {
        if (entity == null) {
            return null;
        }
        return new FormEnderecoCandidatoDTO(
                entity.getActivityDescription(),
                entity.getAddress(),
                entity.getAfterSchoolActivities(),
                entity.getCity(),
                entity.getCommutingTime(),
                entity.getElectricitySource(),
                entity.getHasSewage(),
                entity.getNeighborhood(),
                entity.getReferencePoint(),
                entity.getResidenceType(),
                entity.getStructureType(),
                entity.getStructureTypeOthers(),
                entity.getTransportType(),
                entity.getTransportTypeOthers(),
                entity.getWaterSupply(),
                entity.getWeeklyFrequency(),
                entity.getZipCode(),
                entity.getUser() != null ? entity.getUser().getId() : null
        );
    }
}
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
        entity.setActivityDescription(dto.getActivityDescription());
        entity.setAddress(dto.getAddress());
        entity.setAfterSchoolActivities(dto.getAfterSchoolActivities());
        entity.setCity(dto.getCity());
        entity.setCommutingTime(dto.getCommutingTime());
        entity.setElectricitySource(dto.getElectricitySource());
        entity.setHasSewage(dto.getHasSewage());
        entity.setNeighborhood(dto.getNeighborhood());
        entity.setReferencePoint(dto.getReferencePoint());
        entity.setResidenceType(dto.getResidenceType());
        entity.setStructureType(dto.getStructureType());
        entity.setStructureTypeOthers(dto.getStructureTypeOthers());
        entity.setTransportType(dto.getTransportType());
        entity.setTransportTypeOthers(dto.getTransportTypeOthers());
        entity.setWaterSupply(dto.getWaterSupply());
        entity.setWeeklyFrequency(dto.getWeeklyFrequency());
        entity.setZipCode(dto.getZipCode());
        UserInfo user = new UserInfo();
        user.setId(dto.getUserId());
        entity.setUser(user);
        return entity;
    }

    public FormEnderecoCandidatoDTO toDto(FormEnderecoCandidato entity) {
        if (entity == null) {
            return null;
        }
        FormEnderecoCandidatoDTO dto = new FormEnderecoCandidatoDTO();
        dto.setActivityDescription(entity.getActivityDescription());
        dto.setAddress(entity.getAddress());
        dto.setAfterSchoolActivities(entity.getAfterSchoolActivities());
        dto.setCity(entity.getCity());
        dto.setCommutingTime(entity.getCommutingTime());
        dto.setElectricitySource(entity.getElectricitySource());
        dto.setHasSewage(entity.getHasSewage());
        dto.setNeighborhood(entity.getNeighborhood());
        dto.setReferencePoint(entity.getReferencePoint());
        dto.setResidenceType(entity.getResidenceType());
        dto.setStructureType(entity.getStructureType());
        dto.setStructureTypeOthers(entity.getStructureTypeOthers());
        dto.setTransportType(entity.getTransportType());
        dto.setTransportTypeOthers(entity.getTransportTypeOthers());
        dto.setWaterSupply(entity.getWaterSupply());
        dto.setWeeklyFrequency(entity.getWeeklyFrequency());
        dto.setZipCode(entity.getZipCode());
        if (entity.getUser() != null) {
            dto.setUserId(entity.getUser().getId());
        }
        return dto;
    }
}
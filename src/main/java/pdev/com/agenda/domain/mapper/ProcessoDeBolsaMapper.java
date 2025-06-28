package pdev.com.agenda.domain.mapper;



import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.controller.ProcessoDeBolsa;
import pdev.com.agenda.domain.dto.ProcessoDeBolsaDTO;
import pdev.com.agenda.domain.dto.ProcessoDeBolsaResponse;


@Component
public class ProcessoDeBolsaMapper {

    public ProcessoDeBolsa toEntity(ProcessoDeBolsaDTO dto) {
        ProcessoDeBolsa entity = new ProcessoDeBolsa();
        entity.setVaiParticipar(dto.isVaiParticipar());
        entity.setJaFoiContemplado(dto.isJaFoiContemplado());
        entity.setPercentual(dto.getPercentual());
        return entity;
    }

    public ProcessoDeBolsaResponse toResponse(ProcessoDeBolsa entity) {
        ProcessoDeBolsaResponse response = new ProcessoDeBolsaResponse();
        response.setId(entity.getId());
        response.setVaiParticipar(entity.isVaiParticipar());
        response.setJaFoiContemplado(entity.isJaFoiContemplado());
        response.setPercentual(entity.getPercentual());
        return response;
    }
}


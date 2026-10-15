package pdev.com.agenda.domain.mapper;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.dto.ProcessoDeBolsaDTO;
import pdev.com.agenda.domain.entity.ProcessoDeBolsa;
import pdev.com.agenda.domain.entity.UserInfo;

@Component
@AllArgsConstructor
public class ProcessoDeBolsaMapper {

    public ProcessoDeBolsa toEntity(ProcessoDeBolsaDTO dto) {
        ProcessoDeBolsa entity = new ProcessoDeBolsa();
        entity.setVaiParticipar(dto.vaiParticipar());
        entity.setJaFoiContemplado(dto.jaFoiContemplado());
        entity.setPercentual(dto.percentual());
        entity.setSegmentoAno(dto.segmentoAno());
        entity.setSerieAno(dto.serieAno());

        UserInfo user = new UserInfo();
        user.setId(dto.userId());
        entity.setUser(user);

        return entity;
    }

    public ProcessoDeBolsaDTO toResponse(ProcessoDeBolsa entity) {
        return ProcessoDeBolsaDTO.builder()
                .id(entity.getId())
                .vaiParticipar(entity.isVaiParticipar())
                .jaFoiContemplado(entity.isJaFoiContemplado())
                .percentual(entity.getPercentual())
                .userId(entity.getUser().getId())
                .segmentoAno(entity.getSegmentoAno())
                .serieAno(entity.getSerieAno())
                .build();
    }
}

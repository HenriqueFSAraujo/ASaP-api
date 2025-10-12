package pdev.com.agenda.domain.mapper;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.dto.ProcessoDeBolsaDTO;
import pdev.com.agenda.domain.dto.ProcessoDeBolsaResponse;
import pdev.com.agenda.domain.entity.ProcessoDeBolsa;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.service.UserInfoService;

@Component
@AllArgsConstructor
public class ProcessoDeBolsaMapper {

    private final UserInfoService userService;


    public ProcessoDeBolsa toEntity(ProcessoDeBolsaDTO dto) {
        ProcessoDeBolsa entity = new ProcessoDeBolsa();
        entity.setVaiParticipar(dto.isVaiParticipar());
        entity.setJaFoiContemplado(dto.isJaFoiContemplado());
        entity.setPercentual(dto.getPercentual());
        entity.setSegmentoAno(dto.getSegmentoAno());
        entity.setSerieAno(dto.getSerieAno());

        UserInfo user = new UserInfo();
        user.setId(dto.getUserId());
        entity.setUser(user);

        return entity;
    }

    public ProcessoDeBolsaResponse toResponse(ProcessoDeBolsa entity) {
        ProcessoDeBolsaResponse response = new ProcessoDeBolsaResponse();
        response.setId(entity.getId());
        response.setVaiParticipar(entity.isVaiParticipar());
        response.setJaFoiContemplado(entity.isJaFoiContemplado());
        response.setPercentual(entity.getPercentual());
        response.setUserId(entity.getUser().getId());
        response.setSegmentoAno(entity.getSegmentoAno());
        response.setSerieAno(entity.getSerieAno());

        return response;
    }
}

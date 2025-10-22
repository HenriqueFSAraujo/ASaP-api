package pdev.com.agenda.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
public class ComposicaoFamiliarRequestDTO {

    private Long userInfoId;
    private List<ComposicaoFamiliarDTO> composicaoFamiliar;
}

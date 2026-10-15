package pdev.com.agenda.domain.mapper;

import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.dto.VeiculoDTO;
import pdev.com.agenda.domain.entity.Veiculo;

@Component
public class VeiculoMapper {

    public Veiculo toEntity(VeiculoDTO dto) {
        if (dto == null) return null;
        Veiculo veiculo = new Veiculo();
        veiculo.setId(dto.id());
        veiculo.setMarcaModelo(dto.marcaModelo());
        veiculo.setAnoFabricacao(dto.anoFabricacao());
        veiculo.setUtilizacao(dto.utilizacao());
        return veiculo;
    }

    public VeiculoDTO toDTO(Veiculo veiculo) {
        if (veiculo == null) return null;
        return new VeiculoDTO(
                veiculo.getId(),
                veiculo.getMarcaModelo(),
                veiculo.getAnoFabricacao(),
                veiculo.getUtilizacao()
        );
    }
}

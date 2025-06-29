package pdev.com.agenda.domain.mapper;

import org.springframework.stereotype.Component;
import pdev.com.agenda.domain.dto.VeiculoDTO;
import pdev.com.agenda.domain.entity.Veiculo;

@Component
public class VeiculoMapper {

    public Veiculo toEntity(VeiculoDTO dto) {
        if (dto == null) return null;
        Veiculo veiculo = new Veiculo();
        veiculo.setId(dto.getId());
        veiculo.setMarcaModelo(dto.getMarcaModelo());
        veiculo.setAnoFabricacao(dto.getAnoFabricacao());
        veiculo.setUtilizacao(dto.getUtilizacao());
        return veiculo;
    }

    public VeiculoDTO toDTO(Veiculo veiculo) {
        if (veiculo == null) return null;
        VeiculoDTO dto = new VeiculoDTO();
        dto.setId(veiculo.getId());
        dto.setMarcaModelo(veiculo.getMarcaModelo());
        dto.setAnoFabricacao(veiculo.getAnoFabricacao());
        dto.setUtilizacao(veiculo.getUtilizacao());
        return dto;
    }
}

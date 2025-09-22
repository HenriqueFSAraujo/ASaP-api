package pdev.com.agenda.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.dto.FormEnderecoCandidatoDTO;
import pdev.com.agenda.domain.entity.FormEnderecoCandidato;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.mapper.FormEnderecoCandidatoMapper;
import pdev.com.agenda.domain.repository.FormEnderecoCandidatoRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FormEnderecoCandidatoService {

    private final FormEnderecoCandidatoRepository enderecoRepository;
    private final FormEnderecoCandidatoMapper enderecoMapper;

    @Transactional
    public FormEnderecoCandidatoDTO criarEndereco(FormEnderecoCandidatoDTO enderecoDTO) {
        UserInfo user = new UserInfo();
        user.setId(enderecoDTO.getUserId());
        Optional<FormEnderecoCandidato> existente = enderecoRepository.findByUser(user);
        FormEnderecoCandidato endereco;
        if (existente.isPresent()) {
            endereco = existente.get();
            endereco.setActivityDescription(enderecoDTO.getActivityDescription());
            endereco.setAddress(enderecoDTO.getAddress());
            endereco.setAfterSchoolActivities(enderecoDTO.getAfterSchoolActivities());
            endereco.setCity(enderecoDTO.getCity());
            endereco.setCommutingTime(enderecoDTO.getCommutingTime());
            endereco.setElectricitySource(enderecoDTO.getElectricitySource());
            endereco.setHasSewage(enderecoDTO.getHasSewage());
            endereco.setNeighborhood(enderecoDTO.getNeighborhood());
            endereco.setReferencePoint(enderecoDTO.getReferencePoint());
            endereco.setResidenceType(enderecoDTO.getResidenceType());
            endereco.setStructureType(enderecoDTO.getStructureType());
            endereco.setStructureTypeOthers(enderecoDTO.getStructureTypeOthers());
            endereco.setTransportType(enderecoDTO.getTransportType());
            endereco.setTransportTypeOthers(enderecoDTO.getTransportTypeOthers());
            endereco.setWaterSupply(enderecoDTO.getWaterSupply());
            endereco.setWeeklyFrequency(enderecoDTO.getWeeklyFrequency());
            endereco.setZipCode(enderecoDTO.getZipCode());
        } else {
            endereco = enderecoMapper.toEntity(enderecoDTO);
            endereco.setId(null);
        }

        FormEnderecoCandidato enderecoSalvo = enderecoRepository.save(endereco);
        return enderecoMapper.toDto(enderecoSalvo);
    }

    @Transactional
    public FormEnderecoCandidatoDTO buscarPorUserId(Long userId) {
        FormEnderecoCandidato endereco = enderecoRepository
                .findByUser_Id(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Endereço do candidato não encontrado para o usuário: " + userId));

        return enderecoMapper.toDto(endereco);
    }

    public FormEnderecoCandidatoDTO atualizarEndereco(Long id, FormEnderecoCandidatoDTO enderecoDTO) {

        FormEnderecoCandidato enderecoExistente = enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado com ID: " + id));

        enderecoExistente.setActivityDescription(enderecoDTO.getActivityDescription());
        enderecoExistente.setAddress(enderecoDTO.getAddress());
        enderecoExistente.setAfterSchoolActivities(enderecoDTO.getAfterSchoolActivities());
        enderecoExistente.setCity(enderecoDTO.getCity());
        enderecoExistente.setCommutingTime(enderecoDTO.getCommutingTime());
        enderecoExistente.setElectricitySource(enderecoDTO.getElectricitySource());
        enderecoExistente.setHasSewage(enderecoDTO.getHasSewage());
        enderecoExistente.setNeighborhood(enderecoDTO.getNeighborhood());
        enderecoExistente.setReferencePoint(enderecoDTO.getReferencePoint());
        enderecoExistente.setResidenceType(enderecoDTO.getResidenceType());
        enderecoExistente.setStructureType(enderecoDTO.getStructureType());
        enderecoExistente.setStructureTypeOthers(enderecoDTO.getStructureTypeOthers());
        enderecoExistente.setTransportType(enderecoDTO.getTransportType());
        enderecoExistente.setTransportTypeOthers(enderecoDTO.getTransportTypeOthers());
        enderecoExistente.setWaterSupply(enderecoDTO.getWaterSupply());
        enderecoExistente.setWeeklyFrequency(enderecoDTO.getWeeklyFrequency());
        enderecoExistente.setZipCode(enderecoDTO.getZipCode());

        FormEnderecoCandidato enderecoAtualizado = enderecoRepository.save(enderecoExistente);
        return enderecoMapper.toDto(enderecoAtualizado);
    }


    public List<FormEnderecoCandidatoDTO> listarTodos() {
        return enderecoRepository.findAll()
                .stream()
                .map(enderecoMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deletarEndereco(Long id) {
        if (!enderecoRepository.existsById(id)) {
            throw new EntityNotFoundException("Endereço não encontrado com ID: " + id);
        }
        enderecoRepository.deleteById(id);
    }

    public List<FormEnderecoCandidatoDTO> buscarPorCep(String cep) {
        Optional<FormEnderecoCandidato> result = enderecoRepository.findByZipCode(cep);
        return result.map(enderecoMapper::toDto)
                .stream()
                .collect(Collectors.toList());
    }
}

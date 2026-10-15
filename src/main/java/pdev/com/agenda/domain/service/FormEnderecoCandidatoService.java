package pdev.com.agenda.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.dto.FormEnderecoCandidatoDTO;
import pdev.com.agenda.domain.entity.FormEnderecoCandidato;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.mapper.FormEnderecoCandidatoMapper;
import pdev.com.agenda.domain.repository.FormEnderecoCandidatoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
        user.setId(enderecoDTO.userId());
        Optional<FormEnderecoCandidato> existente = enderecoRepository.findByUser(user);
        FormEnderecoCandidato endereco;
        if (existente.isPresent()) {
            endereco = existente.get();
            endereco.setActivityDescription(enderecoDTO.activityDescription());
            endereco.setAddress(enderecoDTO.address());
            endereco.setAfterSchoolActivities(enderecoDTO.afterSchoolActivities());
            endereco.setCity(enderecoDTO.city());
            endereco.setCommutingTime(enderecoDTO.commutingTime());
            endereco.setElectricitySource(enderecoDTO.electricitySource());
            endereco.setHasSewage(enderecoDTO.hasSewage());
            endereco.setNeighborhood(enderecoDTO.neighborhood());
            endereco.setReferencePoint(enderecoDTO.referencePoint());
            endereco.setResidenceType(enderecoDTO.residenceType());
            endereco.setStructureType(enderecoDTO.structureType());
            endereco.setStructureTypeOthers(enderecoDTO.structureTypeOthers());
            endereco.setTransportType(enderecoDTO.transportType());
            endereco.setTransportTypeOthers(enderecoDTO.transportTypeOthers());
            endereco.setWaterSupply(enderecoDTO.waterSupply());
            endereco.setWeeklyFrequency(enderecoDTO.weeklyFrequency());
            endereco.setZipCode(enderecoDTO.zipCode());
            endereco.setStatus("PENDENTE");
        } else {
            endereco = enderecoMapper.toEntity(enderecoDTO);
            endereco.setId(null);
            endereco.setStatus("PENDENTE");
        }

        FormEnderecoCandidato enderecoSalvo = enderecoRepository.save(endereco);
        return enderecoMapper.toDto(enderecoSalvo);
    }

    @Transactional
    public FormEnderecoCandidatoDTO buscarPorUserId(Long userId) {
        return enderecoRepository
                .findByUser_Id(userId)
                .map(enderecoMapper::toDto)
                .orElse(null);
    }

    public FormEnderecoCandidatoDTO atualizarEndereco(Long id, FormEnderecoCandidatoDTO enderecoDTO) {

        FormEnderecoCandidato enderecoExistente = enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado com ID: " + id));

        enderecoExistente.setActivityDescription(enderecoDTO.activityDescription());
        enderecoExistente.setAddress(enderecoDTO.address());
        enderecoExistente.setAfterSchoolActivities(enderecoDTO.afterSchoolActivities());
        enderecoExistente.setCity(enderecoDTO.city());
        enderecoExistente.setCommutingTime(enderecoDTO.commutingTime());
        enderecoExistente.setElectricitySource(enderecoDTO.electricitySource());
        enderecoExistente.setHasSewage(enderecoDTO.hasSewage());
        enderecoExistente.setNeighborhood(enderecoDTO.neighborhood());
        enderecoExistente.setReferencePoint(enderecoDTO.referencePoint());
        enderecoExistente.setResidenceType(enderecoDTO.residenceType());
        enderecoExistente.setStructureType(enderecoDTO.structureType());
        enderecoExistente.setStructureTypeOthers(enderecoDTO.structureTypeOthers());
        enderecoExistente.setTransportType(enderecoDTO.transportType());
        enderecoExistente.setTransportTypeOthers(enderecoDTO.transportTypeOthers());
        enderecoExistente.setWaterSupply(enderecoDTO.waterSupply());
        enderecoExistente.setWeeklyFrequency(enderecoDTO.weeklyFrequency());
        enderecoExistente.setZipCode(enderecoDTO.zipCode());
        enderecoExistente.setStatus("PENDENTE");

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

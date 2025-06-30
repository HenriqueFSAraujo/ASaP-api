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
        // Criando instância do usuário com base no ID vindo do DTO
        UserInfo user = new UserInfo();
        user.setId(enderecoDTO.getUserId());

        // Buscando se já existe endereço para este usuário
        Optional<FormEnderecoCandidato> existente = enderecoRepository.findByUser(user);

        FormEnderecoCandidato endereco;

        if (existente.isPresent()) {
            endereco = existente.get();
            endereco.setCep(enderecoDTO.getCep());
            endereco.setEndereco(enderecoDTO.getEndereco());
            endereco.setBairro(enderecoDTO.getBairro());
            endereco.setCidade(enderecoDTO.getCidade());
            endereco.setPontoReferencia(enderecoDTO.getPontoReferencia());
            endereco.setResidencia(enderecoDTO.getResidencia());
            // user já está setado
        } else {
            endereco = enderecoMapper.toEntity(enderecoDTO);
            endereco.setId(null); // Força criação de novo
        }

        FormEnderecoCandidato enderecoSalvo = enderecoRepository.save(endereco);
        return enderecoMapper.toDto(enderecoSalvo);
    }

    public FormEnderecoCandidatoDTO atualizarEndereco(Long id, FormEnderecoCandidatoDTO enderecoDTO) {
        //validarEndereco(enderecoDTO);

        FormEnderecoCandidato enderecoExistente = enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado com ID: " + id));

        enderecoExistente.setCep(enderecoDTO.getCep());
        enderecoExistente.setEndereco(enderecoDTO.getEndereco());
        enderecoExistente.setBairro(enderecoDTO.getBairro());
        enderecoExistente.setCidade(enderecoDTO.getCidade());
        enderecoExistente.setPontoReferencia(enderecoDTO.getPontoReferencia());
        enderecoExistente.setResidencia(enderecoDTO.getResidencia());
//        enderecoExistente.setTransporteEducacional(enderecoDTO.getTransporteEducacional());
//        enderecoExistente.setTempoDeslocamento(enderecoDTO.getTempoDeslocamento());
//        enderecoExistente.setAtividadesContraturno(enderecoDTO.getAtividadesContraturno());
//        enderecoExistente.setTelefoneResidencial(enderecoDTO.getTelefoneResidencial());
//        enderecoExistente.setTelefoneTrabalho(enderecoDTO.getTelefoneTrabalho());
//        enderecoExistente.setTelefoneCelular(enderecoDTO.getTelefoneCelular());
//        enderecoExistente.setEmailConfirmacao(enderecoDTO.getEmailConfirmacao());
//        enderecoExistente.setResponsavelLegal(enderecoDTO.getResponsavelLegal());
//        enderecoExistente.setSegmento2025(enderecoDTO.getSegmento2025());

        FormEnderecoCandidato enderecoAtualizado = enderecoRepository.save(enderecoExistente);
        return enderecoMapper.toDto(enderecoAtualizado);
    }


    public FormEnderecoCandidatoDTO buscarPorId(Long id) {
        FormEnderecoCandidato endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado com ID: " + id));

        return enderecoMapper.toDto(endereco);
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
        return enderecoRepository.findByCep(cep)
                .stream()
                .map(enderecoMapper::toDto)
                .collect(Collectors.toList());
    }


    private void validarEndereco(FormEnderecoCandidatoDTO enderecoDTO) {
        if (enderecoDTO.getCep() == null || enderecoDTO.getCep().isBlank()) {
            throw new IllegalArgumentException("CEP é obrigatório");
        }

        if (enderecoDTO.getEndereco() == null || enderecoDTO.getEndereco().isBlank()) {
            throw new IllegalArgumentException("Endereço é obrigatório");
        }

        if (enderecoDTO.getCidade() == null || enderecoDTO.getCidade().isBlank()) {
            throw new IllegalArgumentException("Cidade é obrigatória");
        }


        if (!enderecoDTO.getCep().matches("\\d{5}-?\\d{3}")) {
            throw new IllegalArgumentException("CEP deve estar no formato 00000-000");
        }
    }
}

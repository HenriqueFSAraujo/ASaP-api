package pdev.com.agenda.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.UserInfoResponse;
import pdev.com.agenda.domain.dto.FormsStatusDTO;
import pdev.com.agenda.domain.dto.ResetPasswordRequest;
import pdev.com.agenda.domain.dto.UserInfoDTO;
import pdev.com.agenda.domain.dto.UserInfoWithStatusDTO;
import pdev.com.agenda.domain.entity.BensPosses;
import pdev.com.agenda.domain.entity.DespesaMensal;
import pdev.com.agenda.domain.entity.DocumentosGeraisPdf;
import pdev.com.agenda.domain.entity.Endereco;
import pdev.com.agenda.domain.entity.FormCondicoesHabitacionais;
import pdev.com.agenda.domain.entity.FormDadosParentes;
import pdev.com.agenda.domain.entity.FormDadosPessoais;
import pdev.com.agenda.domain.entity.FormEnderecoCandidato;
import pdev.com.agenda.domain.entity.ProcessoDeBolsa;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.mapper.UserInfoMapper;
import pdev.com.agenda.domain.repository.BensPossesRepository;
import pdev.com.agenda.domain.repository.DespesaMensalRepository;
import pdev.com.agenda.domain.repository.DocumentosGeraisPdfRepository;
import pdev.com.agenda.domain.repository.EnderecoRepository;
import pdev.com.agenda.domain.repository.FormCondicoesHabitacionaisRepository;
import pdev.com.agenda.domain.repository.FormDadosParentesRepository;
import pdev.com.agenda.domain.repository.FormDadosPessoaisRepository;
import pdev.com.agenda.domain.repository.FormEnderecoCandidatoRepository;
import pdev.com.agenda.domain.repository.ProcessoDeBolsaRepository;
import pdev.com.agenda.domain.repository.UserInfoRepository;
import pdev.com.agenda.util.ValidationUtil;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserInfoService {

    private final UserInfoRepository repository;
    private final UserInfoMapper mapper;
    private final BensPossesRepository bensPossesRepository;
    private final DespesaMensalRepository despesaMensalRepository;
    private final DocumentosGeraisPdfRepository documentosGeraisPdfRepository;
    private final EnderecoRepository enderecoRepository;
    private final FormCondicoesHabitacionaisRepository formCondicoesHabitacionaisRepository;
    private final FormDadosParentesRepository formDadosParentesRepository;
    private final FormDadosPessoaisRepository formDadosPessoaisRepository;
    private final FormEnderecoCandidatoRepository formEnderecoCandidatoRepository;
    private final ProcessoDeBolsaRepository processoDeBolsaRepository;


    public List<UserInfoResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UserInfoWithStatusDTO findById(Long id) {
        UserInfo entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));
        FormsStatusDTO status = getFormsAllStatus(id);
        return mapper.toWithStatusDTO(entity, status);
    }

    public FormsStatusDTO getFormsAllStatus(Long userId) {
        FormsStatusDTO dto = new FormsStatusDTO();

        String bensPossesStatus = bensPossesRepository.findByUserInfoId(userId)
                .stream().findFirst().map(BensPosses::getStatus).orElse(null);
        dto.setBensPossesStatus(bensPossesStatus != null ? bensPossesStatus : "PENDENTE");

        String despesaMensalStatus = despesaMensalRepository.findByBensPosses_UserInfoId(userId)
                .stream().findFirst().map(DespesaMensal::getStatus).orElse(null);
        dto.setDespesaMensalStatus(despesaMensalStatus != null ? despesaMensalStatus : "PENDENTE");

        String documentosGeraisPdfStatus = documentosGeraisPdfRepository.findByUserInfoId(userId)
                .map(DocumentosGeraisPdf::getStatus).orElse(null);
        dto.setDocumentosGeraisPdfStatus(documentosGeraisPdfStatus != null ? documentosGeraisPdfStatus : "PENDENTE");

        String enderecoStatus = enderecoRepository.findByUserInfoId(userId)
                .map(Endereco::getStatus).orElse(null);
        dto.setEnderecoStatus(enderecoStatus != null ? enderecoStatus : "PENDENTE");

        String condicoesStatus = formCondicoesHabitacionaisRepository.findByUserId(userId)
                .stream().findFirst().map(FormCondicoesHabitacionais::getStatus).orElse(null);
        dto.setFormCondicoesHabitacionaisStatus(condicoesStatus != null ? condicoesStatus : "PENDENTE");

        String parentesStatus = formDadosParentesRepository.findByUserId(userId)
                .stream().findFirst().map(FormDadosParentes::getStatus).orElse(null);
        dto.setFormDadosParentesStatus(parentesStatus != null ? parentesStatus : "PENDENTE");

        String pessoaisStatus = formDadosPessoaisRepository.findByUserId(userId)
                .stream().findFirst().map(FormDadosPessoais::getStatus).orElse(null);
        dto.setFormDadosPessoaisStatus(pessoaisStatus != null ? pessoaisStatus : "PENDENTE");

        String enderecoCandidatoStatus = formEnderecoCandidatoRepository.findByUserId(userId)
                .stream().findFirst().map(FormEnderecoCandidato::getStatus).orElse(null);
        dto.setFormEnderecoCandidatoStatus(enderecoCandidatoStatus != null ? enderecoCandidatoStatus : "PENDENTE");

        String processoDeBolsaStatus = processoDeBolsaRepository.findByUser_Id(userId)
                .map(ProcessoDeBolsa::getStatus).orElse(null);
        dto.setProcessoDeBolsaStatus(processoDeBolsaStatus != null ? processoDeBolsaStatus : "PENDENTE");

        boolean allPending = "PENDENTE".equals(dto.getBensPossesStatus()) &&
                "PENDENTE".equals(dto.getDespesaMensalStatus()) &&
                "PENDENTE".equals(dto.getDocumentosGeraisPdfStatus()) &&
                "PENDENTE".equals(dto.getEnderecoStatus()) &&
                "PENDENTE".equals(dto.getFormCondicoesHabitacionaisStatus()) &&
                "PENDENTE".equals(dto.getFormDadosParentesStatus()) &&
                "PENDENTE".equals(dto.getFormDadosPessoaisStatus()) &&
                "PENDENTE".equals(dto.getFormEnderecoCandidatoStatus()) &&
                "PENDENTE".equals(dto.getProcessoDeBolsaStatus());

        if (allPending) {
            throw new EntityNotFoundException("Nenhuma entidade encontrada para o usuário: " + userId);
        }
        return dto;
    }

    public UserInfoDTO create(UserInfoDTO dto) {
        validateUserInfo(dto);
        UserInfo entity = mapper.toEntity(dto);
        entity.setStatus("PENDENTE");
        return mapper.toDTO(repository.save(entity));
    }

    public UserInfoDTO update(Long id, UserInfoDTO dto) {
        UserInfo entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));

        validateUserInfo(dto);
        entity.setName(dto.getName());
        entity.setUserName(dto.getUserName());
        entity.setEmail(dto.getEmail());
        entity.setCpf(dto.getCpf());
        entity.setStatus("PENDENTE");
        return mapper.toDTO(repository.save(entity));
    }

    private void validateUserInfo(UserInfoDTO dto) {
        if (!ValidationUtil.isValidEmail(dto.getEmail())) {
            throw new IllegalArgumentException("E-mail inválido.");
        }

        if (!ValidationUtil.isValidCPF(dto.getCpf())) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        if (repository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }
        if (repository.existsByUserName(dto.getUserName())) {
            throw new IllegalArgumentException("Nome de usuário já cadastrado.");
        }
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

    public void resetPassword(Long id, ResetPasswordRequest request) {
        UserInfo user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));

        if (!user.getPassword().equals(request.getCurrentPassWord())) {
            throw new IllegalArgumentException("Senha atual incorreta.");
        }
        user.setFirstLogin(false);
        user.setPassword(request.getNewPassword());
        repository.save(user);
    }

    public void changeUserStatus(Long id, boolean isActive) {
        UserInfo user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));

        user.setActive(isActive);
        repository.save(user);
    }
}

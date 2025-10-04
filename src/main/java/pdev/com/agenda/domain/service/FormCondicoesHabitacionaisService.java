package pdev.com.agenda.domain.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.dto.CondicoesHabitacionaisDTO;
import pdev.com.agenda.domain.entity.FormCondicoesHabitacionais;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.repository.FormCondicoesHabitacionaisRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FormCondicoesHabitacionaisService {


    private FormCondicoesHabitacionaisRepository repository;


    @Transactional
    public CondicoesHabitacionaisDTO salvarOuAtualizar(CondicoesHabitacionaisDTO dto) {
        UserInfo user = new UserInfo();
        user.setId(dto.getUserId());

        Optional<FormCondicoesHabitacionais> existente = repository.findByUser(user);

        FormCondicoesHabitacionais entidade;
        if (existente.isPresent()) {
            entidade = existente.get();
            entidade.setSituacaoImovel(dto.getSituacaoImovel());
            entidade.setTipoImovel(dto.getTipoImovel());
            entidade.setEstruturaFisica(dto.getEstruturaFisica());
            entidade.setEsgotoSanitario(dto.getEsgotoSanitario());
            entidade.setFornecimentoEnergia(dto.getFornecimentoEnergia());
            entidade.setAbastecimentoAgua(dto.getAbastecimentoAgua());
            entidade.setDoencasCronicas(dto.getDoencasCronicas());
            entidade.setCondicoesDeSaudeCasosNaFamilia(dto.getCondicoesDeSaudeCasosNaFamilia());
        } else {
            entidade = new FormCondicoesHabitacionais();
            entidade.setSituacaoImovel(dto.getSituacaoImovel());
            entidade.setTipoImovel(dto.getTipoImovel());
            entidade.setEstruturaFisica(dto.getEstruturaFisica());
            entidade.setEsgotoSanitario(dto.getEsgotoSanitario());
            entidade.setFornecimentoEnergia(dto.getFornecimentoEnergia());
            entidade.setAbastecimentoAgua(dto.getAbastecimentoAgua());
            entidade.setDoencasCronicas(dto.getDoencasCronicas());
            entidade.setCondicoesDeSaudeCasosNaFamilia(dto.getCondicoesDeSaudeCasosNaFamilia());
            entidade.setUser(user);
            entidade.setStatus("ATIVO");
        }

        FormCondicoesHabitacionais salvo = repository.save(entidade);
        return CondicoesHabitacionaisDTO.builder()
                .situacaoImovel(salvo.getSituacaoImovel())
                .tipoImovel(salvo.getTipoImovel())
                .estruturaFisica(salvo.getEstruturaFisica())
                .esgotoSanitario(salvo.getEsgotoSanitario())
                .fornecimentoEnergia(salvo.getFornecimentoEnergia())
                .abastecimentoAgua(salvo.getAbastecimentoAgua())
                .CondicoesDeSaudeCasosNaFamilia(salvo.getCondicoesDeSaudeCasosNaFamilia())
                .doencasCronicas(salvo.getDoencasCronicas())
                .userId(salvo.getUser().getId())
                .build();
    }

    @Transactional
    public Optional<FormCondicoesHabitacionais> getByUserId(Long userId) {
        return repository.findByUser_Id(userId)
                .or(() -> {
                    throw new EntityNotFoundException(
                            "Condições habitacionais não encontradas para o usuário: " + userId
                    );
                });
    }
    public List<FormCondicoesHabitacionais> getAll() {
        return repository.findAll();
    }

    public FormCondicoesHabitacionais update(Long id, CondicoesHabitacionaisDTO dto) {
        Optional<FormCondicoesHabitacionais> optionalEntity = repository.findById(id);
        if (optionalEntity.isPresent()) {
            FormCondicoesHabitacionais entity = optionalEntity.get();
            entity.setSituacaoImovel(dto.getSituacaoImovel());
            entity.setTipoImovel(dto.getTipoImovel());
            entity.setEstruturaFisica(dto.getEstruturaFisica());
            entity.setEsgotoSanitario(dto.getEsgotoSanitario());
            entity.setFornecimentoEnergia(dto.getFornecimentoEnergia());
            entity.setAbastecimentoAgua(dto.getAbastecimentoAgua());
            entity.setStatus("PENDENTE");
            return repository.save(entity);
        }
        return null;
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }

}

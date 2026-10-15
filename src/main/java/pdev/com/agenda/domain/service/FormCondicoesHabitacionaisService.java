package pdev.com.agenda.domain.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.dto.CondicoesHabitacionaisDTO;
import pdev.com.agenda.domain.entity.FormCondicoesHabitacionais;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.repository.FormCondicoesHabitacionaisRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FormCondicoesHabitacionaisService {

    private final FormCondicoesHabitacionaisRepository repository;


    @Transactional
    public CondicoesHabitacionaisDTO salvarOuAtualizar(CondicoesHabitacionaisDTO dto) {
        UserInfo user = new UserInfo();
        user.setId(dto.userId());

        Optional<FormCondicoesHabitacionais> existente = repository.findByUser(user);

        FormCondicoesHabitacionais entidade;
        if (existente.isPresent()) {
            entidade = existente.get();
            entidade.setSituacaoImovel(dto.situacaoImovel());
            entidade.setTipoImovel(dto.tipoImovel());
            entidade.setEstruturaFisica(dto.estruturaFisica());
            entidade.setEsgotoSanitario(dto.esgotoSanitario());
            entidade.setFornecimentoEnergia(dto.fornecimentoEnergia());
            entidade.setAbastecimentoAgua(dto.abastecimentoAgua());
            entidade.setDoencasCronicas(dto.doencasCronicas());
            entidade.setCondicoesDeSaudeCasosNaFamilia(dto.CondicoesDeSaudeCasosNaFamilia());
        } else {
            entidade = new FormCondicoesHabitacionais();
            entidade.setSituacaoImovel(dto.situacaoImovel());
            entidade.setTipoImovel(dto.tipoImovel());
            entidade.setEstruturaFisica(dto.estruturaFisica());
            entidade.setEsgotoSanitario(dto.esgotoSanitario());
            entidade.setFornecimentoEnergia(dto.fornecimentoEnergia());
            entidade.setAbastecimentoAgua(dto.abastecimentoAgua());
            entidade.setDoencasCronicas(dto.doencasCronicas());
            entidade.setCondicoesDeSaudeCasosNaFamilia(dto.CondicoesDeSaudeCasosNaFamilia());
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
            entity.setSituacaoImovel(dto.situacaoImovel());
            entity.setTipoImovel(dto.tipoImovel());
            entity.setEstruturaFisica(dto.estruturaFisica());
            entity.setEsgotoSanitario(dto.esgotoSanitario());
            entity.setFornecimentoEnergia(dto.fornecimentoEnergia());
            entity.setAbastecimentoAgua(dto.abastecimentoAgua());
            entity.setDoencasCronicas(dto.doencasCronicas());
            entity.setCondicoesDeSaudeCasosNaFamilia(dto.CondicoesDeSaudeCasosNaFamilia());
            entity.setStatus("PENDENTE");
            return repository.save(entity);
        }
        return null;
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }

}

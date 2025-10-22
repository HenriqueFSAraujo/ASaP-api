package pdev.com.agenda.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.entity.ComposicaoFamiliar;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.repository.ComposicaoFamiliarRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ComposicaoFamiliarService {

    private final ComposicaoFamiliarRepository repository;

    public List<ComposicaoFamiliar> saveAll(List<ComposicaoFamiliar> composicoes) {
        return repository.saveAll(composicoes);
    }

    public List<ComposicaoFamiliar> findAll() {
        return repository.findAll();
    }

    public List<ComposicaoFamiliar> saveAllForUser(List<ComposicaoFamiliar> composicoes, UserInfo userInfo) {
        composicoes.forEach(composicao -> composicao.setUserInfo(userInfo));
        return repository.saveAll(composicoes);
    }

    public List<ComposicaoFamiliar> findAllByUserComposicao(Long userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        return repository.findAllByUserInfo(userInfo);
    }

    public Optional<ComposicaoFamiliar> findByIdComposicao(Long id) {
        return repository.findById(id);
    }
}

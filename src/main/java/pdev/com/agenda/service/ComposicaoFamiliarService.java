package pdev.com.agenda.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.entity.ComposicaoFamiliar;
import pdev.com.agenda.domain.entity.UserInfo;
import pdev.com.agenda.domain.repository.UserInfoRepository;
import pdev.com.agenda.dto.ComposicaoFamiliarDTO;
import pdev.com.agenda.dto.ComposicaoFamiliarRequestDTO;
import pdev.com.agenda.mapper.ComposicaoFamiliarMapper;
import pdev.com.agenda.repository.ComposicaoFamiliarRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ComposicaoFamiliarService {

    private final ComposicaoFamiliarRepository repository;
    private final UserInfoRepository userInfoRepository;

    public List<ComposicaoFamiliar> saveAll(List<ComposicaoFamiliar> composicoes) {
        return repository.saveAll(composicoes);
    }

    @Transactional
    public List<ComposicaoFamiliar> findAll() {
        return repository.findAll();
    }

    public List<ComposicaoFamiliar> saveAllForUser(List<ComposicaoFamiliar> composicoes, Long userId) {
        UserInfo userInfo = userInfoRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("UserInfo not found with id: " + userId));
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

    public List<ComposicaoFamiliarDTO> saveAllForUser(ComposicaoFamiliarRequestDTO request) {
        Long userId = request.getUserInfoId();
        UserInfo userInfo = userInfoRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("UserInfo not found with id: " + userId));
        List<ComposicaoFamiliar> composicoes = ComposicaoFamiliarMapper.INSTANCE.toEntityList(request.getComposicaoFamiliar());
        composicoes.forEach(composicao -> composicao.setUserInfo(userInfo));
        List<ComposicaoFamiliar> savedComposicoes = repository.saveAll(composicoes);
        return ComposicaoFamiliarMapper.INSTANCE.toDTOList(savedComposicoes);
    }

    public List<ComposicaoFamiliarDTO> getAllByUser(Long userId) {
        UserInfo userInfo = userInfoRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("UserInfo not found with id: " + userId));
        List<ComposicaoFamiliar> composicoes = repository.findAllByUserInfo(userInfo);
        return ComposicaoFamiliarMapper.INSTANCE.toDTOList(composicoes);
    }
}

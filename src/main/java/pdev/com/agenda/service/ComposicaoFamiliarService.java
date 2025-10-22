package pdev.com.agenda.service;

import org.springframework.stereotype.Service;
import pdev.com.agenda.domain.entity.ComposicaoFamiliar;
import pdev.com.agenda.repository.ComposicaoFamiliarRepository;

import java.util.List;

@Service
public class ComposicaoFamiliarService {

    private final ComposicaoFamiliarRepository repository;

    public ComposicaoFamiliarService(ComposicaoFamiliarRepository repository) {
        this.repository = repository;
    }

    public List<ComposicaoFamiliar> saveAll(List<ComposicaoFamiliar> composicoes) {
        return repository.saveAll(composicoes);
    }
}

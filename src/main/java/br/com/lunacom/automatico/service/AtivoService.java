package br.com.lunacom.automatico.service;

import br.com.lunacom.automatico.domain.entity.Ativo;
import br.com.lunacom.automatico.repository.AtivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AtivoService {

    private final AtivoRepository repository;

    @Transactional
    public void atualizarUltimaAtualizacao(Ativo ativo) {
        repository.updateUltimaAtualizacao(ativo.getCodigo(), ativo.getUltimaAtualizacao());
    }

}

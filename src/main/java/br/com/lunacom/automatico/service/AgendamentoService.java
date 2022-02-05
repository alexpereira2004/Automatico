package br.com.lunacom.automatico.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AgendamentoService {

    public void dispararBuscaDadosCotacoes() {
        log.info("LOG Disparado com sucesso");
    }

    public long definirTempoParaProximoDisparo() {
        return 60000L;
    }
}

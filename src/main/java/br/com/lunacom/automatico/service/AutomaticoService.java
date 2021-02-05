package br.com.lunacom.automatico.service;

import br.com.lunacom.automatico.client.LeitorDeIndicesClient;
import br.com.lunacom.automatico.domain.dto.AtivoResumoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AutomaticoService {

    @Autowired
    private final LeitorDeIndicesClient leitorDeIndicesClient;

    public void teste() {
        final ResponseEntity<List<AtivoResumoDto>> listResponseEntity = leitorDeIndicesClient.findTodosComCotacao();
        return;
    }
}

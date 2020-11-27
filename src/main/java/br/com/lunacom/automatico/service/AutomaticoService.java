package br.com.lunacom.automatico.service;

import br.com.lunacom.automatico.client.LeitorDeIndicesClient;
import br.com.lunacom.automatico.domain.dto.CotacaoAtivoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AutomaticoService {

    private final LeitorDeIndicesClient leitorDeIndicesClient;

    public void teste() {
        final ResponseEntity<List<CotacaoAtivoDto>> response = leitorDeIndicesClient.find(
                "TSLA34",
                "01/10/2020",
                "01/11/2020");
        return;
    }
}

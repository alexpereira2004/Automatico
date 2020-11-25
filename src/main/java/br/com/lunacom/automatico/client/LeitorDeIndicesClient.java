package br.com.lunacom.automatico.client;

import br.com.lunacom.automatico.domain.dto.CotacaoAtivoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "leitor-de-indices", url = "localhost:8081", path = "/cotacao")
//@FeignClient(name = "hr-worker", path = "/workers")
public interface LeitorDeIndicesClient {

    @RequestMapping(method= RequestMethod.GET)
    ResponseEntity<List<CotacaoAtivoDto>> find(@RequestParam("ativo") String ativo,
                                               @RequestParam("datainicial") String datainicial,
                                               @RequestParam("datafinal") String datafinal);
}

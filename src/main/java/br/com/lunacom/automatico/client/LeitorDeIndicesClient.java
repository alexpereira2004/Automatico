package br.com.lunacom.automatico.client;

import br.com.lunacom.automatico.domain.dto.AtivoResumoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component

@FeignClient(
        name = "leitor-de-indices",
        url = "localhost:8765",
        path = "/leitor-de-indices/scraping")
//@FeignClient(name = "hr-worker", path = "/workers")
public interface LeitorDeIndicesClient {

    @RequestMapping(method= RequestMethod.GET, path = "/ativo/todos-com-cotacao")
    ResponseEntity<List<AtivoResumoDto>> findTodosComCotacao();

    @RequestMapping(method= RequestMethod.GET, path = "/historico/ativos/")
    ResponseEntity<Void> scrapingHistoricoAtivos(
            @RequestParam("ativos") List<String> listaAtivos,
            @RequestParam("inicio") String inicio,
            @RequestParam("invisivel") Boolean invisivel,
            @RequestParam("site") String site
    );
}

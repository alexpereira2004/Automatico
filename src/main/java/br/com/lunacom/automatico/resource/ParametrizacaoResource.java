package br.com.lunacom.automatico.resource;

import br.com.lunacom.automatico.service.AutomaticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/parametrizacao")
public class ParametrizacaoResource {

    @Autowired
    private AutomaticoService automaticoService;

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<Void> find() {
        automaticoService.teste();
        return ResponseEntity.ok().build();
    }
}

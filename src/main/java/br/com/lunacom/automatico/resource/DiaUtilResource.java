package br.com.lunacom.automatico.resource;

import br.com.lunacom.automatico.domain.entity.DiaUtil;
import br.com.lunacom.automatico.service.DiaUtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/dias-uteis")
public class DiaUtilResource {

    final DiaUtilService service;

    @PostMapping(value = "/v1/parse-from-dias-uteis-com")
    public ResponseEntity<Void> parseFromDiasUteisCom(@RequestBody @Valid @NotNull String request) {
        List<DiaUtil> list = service.parseFromDiasUteisCom(request);
        return new ResponseEntity(list, HttpStatus.OK);
    }
}

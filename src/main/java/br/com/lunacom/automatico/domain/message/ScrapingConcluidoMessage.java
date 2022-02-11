package br.com.lunacom.automatico.domain.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Builder
@Component
public class ScrapingConcluidoMessage {
    private String codigo;
    private String resultado;
    private LocalDateTime ultimaAtualizacao;

    public ScrapingConcluidoMessage(@JsonProperty("ativo") String ativo,
                                    @JsonProperty("resultado") String resultado,
                                    @JsonProperty("dia") LocalDateTime dia) {
        this.codigo = ativo;
        this.resultado = resultado;
        this.ultimaAtualizacao = dia;
    }

}

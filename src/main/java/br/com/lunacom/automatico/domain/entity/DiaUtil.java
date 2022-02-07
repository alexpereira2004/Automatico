package br.com.lunacom.automatico.domain.entity;

import br.com.lunacom.automatico.enumeration.TipoDiaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaUtil implements Serializable {

    @Id
    private LocalDate dia;
    private Integer  dia_ano;
    private Integer  semana;
    private TipoDiaEnum tipo;
    private String feriado;
}

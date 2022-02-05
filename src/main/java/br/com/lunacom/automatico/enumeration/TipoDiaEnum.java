package br.com.lunacom.automatico.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;

@AllArgsConstructor
@Getter
public enum TipoDiaEnum {
    NORMAL(1, "Normal"),
    FIM_SEMANA(2, "Fim de semana"),
    FERIADO(3, "Feriado");

    Integer codigo;
    String descricao;

    public static TipoDiaEnum fromCodigo(Integer value) {
        return EnumSet.allOf(TipoDiaEnum.class)
                .stream()
                .filter(it -> it.getCodigo().equals(value))
                .findFirst()
                .orElse(NORMAL);
    }
}

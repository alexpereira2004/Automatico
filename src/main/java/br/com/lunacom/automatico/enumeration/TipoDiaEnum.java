package br.com.lunacom.automatico.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;

@AllArgsConstructor
@Getter
public enum TipoDiaEnum {
    NORMAL(1, "Normal", "white"),
    FIM_SEMANA(2, "Fim de semana", "gray"),
    FERIADO(3, "Feriado", "red");

    Integer codigo;
    String descricao;
    String flag;

    public static TipoDiaEnum fromCodigo(Integer value) {
        return EnumSet.allOf(TipoDiaEnum.class)
                .stream()
                .filter(it -> it.getCodigo().equals(value))
                .findFirst()
                .orElse(NORMAL);
    }

    public static TipoDiaEnum fromFlag(String value) {
        return EnumSet.allOf(TipoDiaEnum.class)
                .stream()
                .filter(it -> it.getFlag().equals(value))
                .findFirst()
                .orElse(NORMAL);
    }
}

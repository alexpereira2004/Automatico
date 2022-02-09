package br.com.lunacom.automatico.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SeguindoEnum {
    SIM("S"),
    NAO("N");

    String codigo;
}

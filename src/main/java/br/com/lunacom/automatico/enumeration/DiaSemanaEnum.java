package br.com.lunacom.automatico.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DiaSemanaEnum {
    SEGUNDA(0,"Segunda"),
    TERCA(1, "Terça"),
    QUARTA(2, "Quarta"),
    QUINTA(3, "Quinta"),
    SEXTA(4, "Sexta"),
    SABADO(5, "Sábado"),
    DOMINGO(6, "Domingo"),
    TODOS(7, "Todos os dias");

    Integer codigo;
    String descricao;


}

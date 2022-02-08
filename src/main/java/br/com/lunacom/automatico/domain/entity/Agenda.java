package br.com.lunacom.automatico.domain.entity;

import br.com.lunacom.automatico.enumeration.DiaSemanaEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Data
public class Agenda implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private LocalTime inicio;
    private LocalTime fim;
    private Integer periodicidade;
    private DiaSemanaEnum dia;
}

package br.com.lunacom.automatico.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ativo implements Serializable {

    @Id
    private String codigo;
    private String nome;
    private LocalDateTime ultimaAtualizacao;
    @Column(length=1)
    private String seguindo;
}

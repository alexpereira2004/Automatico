package br.com.lunacom.automatico.repository;

import br.com.lunacom.automatico.domain.entity.Agenda;
import br.com.lunacom.automatico.enumeration.DiaSemanaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer> {
    Optional<Agenda> findByInicioBeforeAndFimAfter(LocalTime horaReferenciaInicio, LocalTime horaReferenciaFim);

    Optional<Agenda> findByDia(DiaSemanaEnum dia);
}

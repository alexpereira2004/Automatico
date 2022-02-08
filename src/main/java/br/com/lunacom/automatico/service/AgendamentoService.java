package br.com.lunacom.automatico.service;

import br.com.lunacom.automatico.domain.entity.Agenda;
import br.com.lunacom.automatico.enumeration.DiaSemanaEnum;
import br.com.lunacom.automatico.repository.AgendaRepository;
import br.com.lunacom.automatico.util.DataUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class AgendamentoService {

    private final AgendaRepository repository;
    private final DataUtil dataUtil;

    public void dispararBuscaDadosCotacoes() {
        log.info("LOG Disparado com sucesso");
    }

    public long definirTempoParaProximoDisparo() {
        return 60000L;
    }

    public Instant definirTempoParaProximoDisparo(Optional<Date> lastCompletionTime) {
        Date date = lastCompletionTime.orElseGet(Date::new);

        LocalDateTime ultimaExecucao = date
                .toInstant().atZone(ZoneId.of("America/Sao_Paulo"))
                .toLocalDateTime();

        Optional<Agenda> agendaOptional = repository.findByDia(DiaSemanaEnum.TODOS);
        Agenda agenda = agendaOptional.get();

        LocalDateTime proximaExecucaoCalculada = ultimaExecucao.plusMinutes(agenda.getPeriodicidade());

        if (proximaExecucaoCalculada.toLocalTime().isAfter(agenda.getFim())) {
            LocalDate proximoDiaUtil = dataUtil.proximoDiaUtil(ultimaExecucao.toLocalDate());
            proximaExecucaoCalculada = LocalDateTime.of(proximoDiaUtil, agenda.getInicio());
        }

        return proximaExecucaoCalculada.toInstant(ZoneOffset.of("-03:00"));
    }
}

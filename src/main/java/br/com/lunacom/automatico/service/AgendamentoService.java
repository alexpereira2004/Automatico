package br.com.lunacom.automatico.service;

import br.com.lunacom.automatico.amq.producer.ScrapingHistoricoIndicesProducer;
import br.com.lunacom.automatico.domain.entity.Agenda;
import br.com.lunacom.automatico.domain.entity.Ativo;
import br.com.lunacom.automatico.domain.message.SolicitacaoScrapingDto;
import br.com.lunacom.automatico.enumeration.DiaSemanaEnum;
import br.com.lunacom.automatico.enumeration.SeguindoEnum;
import br.com.lunacom.automatico.repository.AgendaRepository;
import br.com.lunacom.automatico.repository.AtivoRepository;
import br.com.lunacom.automatico.util.DataUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class AgendamentoService {

    private final AgendaRepository repository;
    private final AtivoRepository ativoRepository;
    private final DataUtil dataUtil;
    private final ScrapingHistoricoIndicesProducer producer;


    public void dispararBuscaDadosCotacoes() {
        List<Ativo> list = ativoRepository.findAllBySeguindo(SeguindoEnum.SIM.getCodigo());
        List<Ativo> ativosSemDadosNoDia = list.stream()
                .filter(ativo ->Objects.isNull(ativo.getUltimaAtualizacao())
                        || ativo.getUltimaAtualizacao().toLocalDate().isBefore(DataUtil.dataAgora()))
                .collect(Collectors.toList());

        ativosSemDadosNoDia.forEach(a -> producer.produce(new SolicitacaoScrapingDto(a.getCodigo())));
    }

    public LocalDateTime definirTempoParaProximoDisparo(Optional<Date> lastCompletionTime) {
        final Date ultimaExecucao = lastCompletionTime.orElse(null);
        final Agenda agenda = getAgendaNoBancoDeDados();
        LocalDateTime proximaExecucaoCalculada = calcularProximaExecucao(ultimaExecucao, agenda);
        final LocalDateTime ultimaExecucaoLocalDateTime = Objects.isNull(ultimaExecucao)
                ? DataUtil.dataHoraAgora() : parseDateToLocalTime(ultimaExecucao);
        proximaExecucaoCalculada = garantirQueProximaExecucaoRespeiteAgenda
                (ultimaExecucaoLocalDateTime, agenda, proximaExecucaoCalculada);
        return proximaExecucaoCalculada;
    }

    private LocalDateTime parseDateToLocalTime(Date date) {

        return date
                .toInstant().atZone(ZoneId.of("America/Sao_Paulo"))
                .toLocalDateTime();
    }

    private Agenda getAgendaNoBancoDeDados() {
        Optional<Agenda> agendaOptional = repository.findByDia(DiaSemanaEnum.TODOS);
        Agenda agenda = agendaOptional.get();
        return agenda;
    }

    private LocalDateTime calcularProximaExecucao(Date ultimaExecucao, Agenda agenda) {
        LocalDateTime proximaExecucaoCalculada = null;
        if (Objects.isNull(ultimaExecucao)) {
            proximaExecucaoCalculada = DataUtil.dataHoraAgora().plusMinutes(1L);
        } else {
            proximaExecucaoCalculada = parseDateToLocalTime(ultimaExecucao).plusMinutes(agenda.getPeriodicidade());
        }
        return proximaExecucaoCalculada;
    }

    private LocalDateTime garantirQueProximaExecucaoRespeiteAgenda(LocalDateTime ultimaExecucao,
                                                                   Agenda agenda,
                                                                   LocalDateTime proximaExecucaoCalculada) {
        if (proximaExecucaoCalculada.toLocalTime().isAfter(agenda.getFim())) {
            LocalDate proximoDiaUtil = dataUtil.proximoDiaUtil(ultimaExecucao.toLocalDate());
            proximaExecucaoCalculada = LocalDateTime.of(proximoDiaUtil, agenda.getInicio());
        }

        if (proximaExecucaoCalculada.toLocalTime().isBefore(agenda.getInicio())) {
            proximaExecucaoCalculada = LocalDateTime.of(proximaExecucaoCalculada.toLocalDate(), agenda.getInicio());
        }
        return proximaExecucaoCalculada;
    }
}

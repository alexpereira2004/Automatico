package br.com.lunacom.automatico.task;

import br.com.lunacom.automatico.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class DynamicSchedulingConfig implements SchedulingConfigurer {

    @Autowired
    private AgendamentoService agendamentoService;

    @Bean
    public Executor taskExecutor() {
        return Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
        scheduledTaskRegistrar.addTriggerTask(
                new Runnable() {
                    @Override
                    public void run() {
                        agendamentoService.dispararBuscaDadosCotacoes();
                    }
                },
                new Trigger() {
                    @Override
                    public Date nextExecutionTime(TriggerContext context) {
                        Optional<Date> lastCompletionTime =
                                Optional.ofNullable(context.lastCompletionTime());
                        Instant nextExecutionTime =
                                lastCompletionTime.orElseGet(Date::new).toInstant()
                                        .plusMillis(agendamentoService.definirTempoParaProximoDisparo());
                        return Date.from(nextExecutionTime);
                    }
                }
        );
    }
}
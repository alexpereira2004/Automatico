package br.com.lunacom.automatico.amq.consumer;

import br.com.lunacom.automatico.domain.entity.Ativo;
import br.com.lunacom.automatico.domain.message.ScrapingConcluidoMessage;
import br.com.lunacom.automatico.service.AtivoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScrapingConcluidoConsumer {

    private final AtivoService service;

    private final ModelMapper modelMapper;

    @RabbitListener(queues = "${rabbitmq.queue.in}")
    public void listen(ScrapingConcluidoMessage mensagem) {
        log.info(String.valueOf(mensagem));
        Ativo ativo = modelMapper.map(mensagem, Ativo.class);
        service.atualizarUltimaAtualizacao(ativo);
    }
}

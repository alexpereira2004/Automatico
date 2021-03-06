package br.com.lunacom.automatico.amq.producer;

import br.com.lunacom.automatico.domain.message.SolicitacaoScrapingDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScrapingHistoricoIndicesProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingkey.solicitacao}")
    private String routingkey;

    public void produce(SolicitacaoScrapingDto data){
        amqpTemplate.convertAndSend(exchange, routingkey, data);
        log.info("Send msg = " + data);
    }
}

package br.com.lunacom.automatico.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQProducerConfig {

    @Value("${rabbitmq.queue.in}")
    String queueNameInput;

    @Value("${rabbitmq.queue.out}")
    String queueNameOutput;

    @Value("${rabbitmq.exchange}")
    String exchange;

    @Value("${rabbitmq.routingkey.solicitacao}")
    private String routingKeySolicitacao;

    @Value("${rabbitmq.routingkey.concluido}")
    private String routingKeyConclusao;

    @Bean("exchangeCreate")
    public DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Queue resultadoScrapingQueue() {
        return new Queue(queueNameInput, false);
    }

    @Bean
    public Binding bindingInput(@Qualifier("resultadoScrapingQueue") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKeyConclusao);
    }

    @Bean
    public Queue solicitacaoScrapingQueue() {
        return new Queue(queueNameOutput, false);
    }

    @Bean
    public Binding bindingOutput(@Qualifier("solicitacaoScrapingQueue") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKeySolicitacao);
    }
}

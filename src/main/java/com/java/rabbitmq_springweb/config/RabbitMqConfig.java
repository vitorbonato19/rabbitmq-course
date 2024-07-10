package com.java.rabbitmq_springweb.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class RabbitMqConfig {

    private final ConnectionFactory connectionFactory;

    @Value("${rabbitmq.exchange.propostapendente}")
    private String exchangePropostaPendente;

    @Value("${rabbitmq.exchange.propostaconcluida}")
    private String exchangePropostaConcluida;

    public RabbitMqConfig(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Bean
    public RabbitAdmin criaRabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Queue criarFilaPropostaPendenteMsAnaliseCredito() {
        return QueueBuilder.durable("proposta-pendente.ms-analise-credito")
                .ttl(10000)
                .maxLength(5L)
                .deadLetterExchange("proposta-pendente-dlx.ex")
                .build();
    }

    @Bean
    public Queue criarFilaPropostaConcluidaMsProposta() {
        return QueueBuilder.durable("proposta-concluida.ms-proposta")
                .ttl(10000)
                .maxLength(5L)
                .deadLetterExchange("proposta-concluida-dlx.ex")
                .build();
    }

    @Bean
    public Queue filaPropostaPendenteDLQ() {
        return QueueBuilder.durable("proposta-pendente.dlq").build();
    }

    @Bean
    public Queue filaPropostaConcluidaDLQ() {
        return QueueBuilder.durable("proposta-concluida.dlq").build();
    }

    @Bean
    public Queue criarFilaPropostaPendenteMsNotificacao() {
        return QueueBuilder.durable("proposta-pendente.ms-notificacao")
                .ttl(10000)
                .maxLength(5L)
                .deadLetterExchange("proposta-pendente-dlx.ex")
                .build();
    }

    @Bean
    public Queue criarFilaPropostaConcluidaMsNotificacao() {
        return QueueBuilder.durable("proposta-concluida.ms-notificacao")
                .ttl(10000)
                .maxLength(5L)
                .deadLetterExchange("proposta-concluida-dlx.ex")
                .build();
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> startarAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public FanoutExchange criarExchangeFanoutPropostaPendente() {
        return ExchangeBuilder.fanoutExchange(exchangePropostaPendente).build();
    }

    @Bean
    public FanoutExchange exchangeProposatPendenteDLQ() {
        return ExchangeBuilder.fanoutExchange("proposta-pendente-dlx.ex").build();
    }

    @Bean
    public FanoutExchange exchangePropostaConcluidaDLQ() {
        return ExchangeBuilder.fanoutExchange("proposta-concluida-dlx.ex").build();
    }

    @Bean
    public FanoutExchange criarExchangeFanoutPropostaConcluida() {
        return ExchangeBuilder.fanoutExchange(exchangePropostaConcluida).build();
    }


    @Bean
    public Binding criarBindingPropostaPendenteMSAnaliseCredito() {
        return BindingBuilder.bind(criarFilaPropostaPendenteMsAnaliseCredito())
                .to(criarExchangeFanoutPropostaPendente());
    }

    @Bean
    public Binding bindingPropostaPendenteDLQ() {
        return BindingBuilder.bind(filaPropostaPendenteDLQ())
                .to(exchangeProposatPendenteDLQ());
    }

    @Bean
    public Binding bindingPropostaConcluidaDLQ() {
        return BindingBuilder.bind(filaPropostaConcluidaDLQ())
                .to(exchangePropostaConcluidaDLQ());
    }

    @Bean
    public Binding criarBindingPropostaPendenteMSNotificacao() {
        return BindingBuilder.bind(criarFilaPropostaPendenteMsNotificacao())
                .to(criarExchangeFanoutPropostaPendente());
    }

    @Bean
    public Binding criarBindingPropostaConcluidaMSPropostaApp() {
        return BindingBuilder.bind(criarFilaPropostaConcluidaMsProposta())
                .to(criarExchangeFanoutPropostaConcluida());
    }

    @Bean
    public Binding criarBindingPropostaConcluidaMSNotificacao() {
        return BindingBuilder.bind(criarFilaPropostaConcluidaMsNotificacao())
                .to(criarExchangeFanoutPropostaConcluida());
    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());

        return rabbitTemplate;
    }
}

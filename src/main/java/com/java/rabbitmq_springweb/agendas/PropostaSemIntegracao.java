package com.java.rabbitmq_springweb.agendas;

import com.java.rabbitmq_springweb.entity.Proposta;
import com.java.rabbitmq_springweb.repository.PropostaRepository;
import com.java.rabbitmq_springweb.service.NotificaRabbitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class PropostaSemIntegracao {

    private final NotificaRabbitService notificaRabbitService;

    private final PropostaRepository propostaRepository;

    @Value("${rabbitmq.exchange.propostapendente}")
    private final String exchange;

   private final Logger logger = LoggerFactory.getLogger(PropostaSemIntegracao.class);

    public PropostaSemIntegracao(NotificaRabbitService notificaRabbitService, PropostaRepository propostaRepository, String exchange) {
        this.notificaRabbitService = notificaRabbitService;
        this.propostaRepository = propostaRepository;
        this.exchange = exchange;
    }

    @Scheduled(fixedDelay = 15, timeUnit = TimeUnit.SECONDS)
    public void buscarPropostasSemIntegracao() {
        propostaRepository.findAllByIntegradaIsFalse().forEach(proposta -> {
            try {
                notificaRabbitService.notificar(proposta, exchange);
                atualizaIntegradaParaTrue(proposta);
            } catch (RuntimeException ex) {
                logger.error(ex.getMessage());
            }

        });

    }

    public void atualizaIntegradaParaTrue(Proposta proposta) {
        proposta.setIntegrada(true);
        propostaRepository.save(proposta);
    }
}

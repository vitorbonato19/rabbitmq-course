package com.java.rabbitmq_springweb.listener;

import com.java.rabbitmq_springweb.entity.Proposta;
import com.java.rabbitmq_springweb.mapper.PropostaMapper;
import com.java.rabbitmq_springweb.repository.PropostaRepository;
import com.java.rabbitmq_springweb.service.WebSocketService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PropostaConcluidaListener {

    private final PropostaRepository propostaRepository;

    private final WebSocketService webSocketService;

    public PropostaConcluidaListener(PropostaRepository propostaRepository, WebSocketService webSocketService) {
        this.propostaRepository = propostaRepository;
        this.webSocketService = webSocketService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void salvaPropostaConcluida(Proposta proposta) {
        propostaRepository.save(proposta);
        var dto = PropostaMapper.instance.convertEntityToDto(proposta);
        webSocketService.notificar(dto);
    }
}

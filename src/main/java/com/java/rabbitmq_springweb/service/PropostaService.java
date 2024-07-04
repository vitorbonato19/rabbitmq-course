package com.java.rabbitmq_springweb.service;

import com.java.rabbitmq_springweb.controller.dto.PropostaRequestDto;
import com.java.rabbitmq_springweb.controller.dto.PropostaResponseDto;
import com.java.rabbitmq_springweb.entity.Proposta;
import com.java.rabbitmq_springweb.mapper.PropostaMapper;
import com.java.rabbitmq_springweb.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    private final PropostaRepository propostaRepository;

    private final NotificaRabbitService notificaRabbitService;

    private String exchange;

    public PropostaService(PropostaRepository propostaRepository,
                           NotificaRabbitService notificaRabbitService,
                           @Value("${rabbitmq.exchange.propostapendente}") String exchange) {
        this.propostaRepository = propostaRepository;
        this.notificaRabbitService = notificaRabbitService;
        this.exchange = exchange;
    }

    public void notificar(Proposta proposta) {
        try {
            notificaRabbitService.notificar(proposta, exchange);
        } catch (RuntimeException ex) {
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }

    }

    public PropostaResponseDto criar(PropostaRequestDto requestDto) {

        var proposta = PropostaMapper.instance.convertoDtoToProposta(requestDto);
        propostaRepository.save(proposta);
        notificar(proposta);

        var response = PropostaMapper.instance.convertEntityToDto(proposta);
        return response;
    }

    public List<PropostaResponseDto> retornaPropostas() {
        return PropostaMapper.instance.convertListEntityToListDto(propostaRepository.findAll());
    }
}

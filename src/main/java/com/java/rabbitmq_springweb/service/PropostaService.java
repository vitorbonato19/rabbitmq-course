package com.java.rabbitmq_springweb.service;

import com.java.rabbitmq_springweb.controller.dto.PropostaRequestDto;
import com.java.rabbitmq_springweb.controller.dto.PropostaResponseDto;
import com.java.rabbitmq_springweb.mapper.PropostaMapper;
import com.java.rabbitmq_springweb.repository.PropostaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    private final PropostaRepository propostaRepository;

    private final NotificacaoService notificacaoService;

    public PropostaService(PropostaRepository propostaRepository, NotificacaoService notificacaoService) {
        this.propostaRepository = propostaRepository;
        this.notificacaoService = notificacaoService;
    }

    public PropostaResponseDto criar(PropostaRequestDto requestDto) {

        var proposta = PropostaMapper.instance.convertoDtoToProposta(requestDto);
        propostaRepository.save(proposta);

        var response = PropostaMapper.instance.convertEntityToDto(proposta);
        notificacaoService.notificar(response, "proposta-pendente.ex");

        return response;
    }

    public List<PropostaResponseDto> retornaPropostas() {
        return PropostaMapper.instance.convertListEntityToListDto(propostaRepository.findAll());
    }
}

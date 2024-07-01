package com.java.rabbitmq_springweb.service;

import com.java.rabbitmq_springweb.controller.dto.PropostaRequestDto;
import com.java.rabbitmq_springweb.controller.dto.PropostaResponseDto;
import com.java.rabbitmq_springweb.entity.Proposta;
import com.java.rabbitmq_springweb.repository.PropostaRepository;
import org.springframework.stereotype.Service;

@Service
public class PropostaService {

    private final PropostaRepository propostaRepository;

    public PropostaService(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    public PropostaResponseDto criar(PropostaRequestDto requestDto) {
        propostaRepository.save(new Proposta());
        return null;
    }
}

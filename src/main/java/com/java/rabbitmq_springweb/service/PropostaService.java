package com.java.rabbitmq_springweb.service;

import com.java.rabbitmq_springweb.controller.dto.PropostaRequestDto;
import com.java.rabbitmq_springweb.controller.dto.PropostaResponseDto;
import com.java.rabbitmq_springweb.entity.Proposta;
import com.java.rabbitmq_springweb.mapper.PropostaMapper;
import com.java.rabbitmq_springweb.repository.PropostaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    private final PropostaRepository propostaRepository;

    public PropostaService(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    public PropostaResponseDto criar(PropostaRequestDto requestDto) {
        var proposta = PropostaMapper.instance.convertoDtoToProposta(requestDto);
        propostaRepository.save(proposta);
        PropostaResponseDto response = PropostaMapper.instance.convertEntityToDto(proposta);
        return response;
    }

    public List<PropostaResponseDto> retornaPropostas() {
        return PropostaMapper.instance.convertListEntityToListDto(propostaRepository.findAll());
    }
}

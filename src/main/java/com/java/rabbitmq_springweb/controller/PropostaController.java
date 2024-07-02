package com.java.rabbitmq_springweb.controller;

import com.java.rabbitmq_springweb.controller.dto.PropostaRequestDto;
import com.java.rabbitmq_springweb.controller.dto.PropostaResponseDto;
import com.java.rabbitmq_springweb.service.PropostaService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    private final PropostaService service;

    public PropostaController(PropostaService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<PropostaResponseDto> criar(@RequestBody PropostaRequestDto requestDto) {
        PropostaResponseDto response = service.criar(requestDto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").
                buildAndExpand(response.getId())
                .toUri()).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PropostaResponseDto>> retornaPropostas() {
        return ResponseEntity.ok().body(service.retornaPropostas());
    }
}

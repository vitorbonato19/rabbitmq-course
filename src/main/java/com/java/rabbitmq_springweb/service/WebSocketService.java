package com.java.rabbitmq_springweb.service;

import com.java.rabbitmq_springweb.controller.dto.PropostaResponseDto;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void notificar(PropostaResponseDto responseDto) {
        simpMessagingTemplate.convertAndSend("/propostas", responseDto);
    }
}

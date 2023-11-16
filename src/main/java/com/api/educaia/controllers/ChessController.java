package com.api.educaia.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChessController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChessController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/sendMove") // Mapeia as mensagens enviadas com esse destino
    public void sendMove(String move) {
        String response = "Movimento Recebido: " + move;

        // Envie a resposta para o tópico '/topic/gameUpdates'
        messagingTemplate.convertAndSend("/topic/gameUpdates", response);
    }
}

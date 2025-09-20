package com.albertoandraul.arfit.controller;

import com.albertoandraul.arfit.dto.ChatMessageDTO;
import com.albertoandraul.arfit.model.Message;
import com.albertoandraul.arfit.repository.MessageRepository;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class ChatWebSocketController {

    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatWebSocketController(MessageRepository messageRepository,
                                   SimpMessagingTemplate messagingTemplate) {
        this.messageRepository = messageRepository;
        this.messagingTemplate = messagingTemplate;
    }

    // Recibir mensaje en un chat específico
    @MessageMapping("/chat/{chatId}/send")
    public void sendMessage(@DestinationVariable Long chatId, ChatMessageDTO chatMessageDTO) {
        // Crear mensaje
        Message message = new Message();
        message.setChatId(chatId);
        message.setSenderId(chatMessageDTO.getSenderId());
        message.setContent(chatMessageDTO.getContent());
        message.setCreatedAt(new Date());
        message.setSeen(false);

        // Guardar en la DB
        Message savedMessage = messageRepository.save(message);

        // Enviar solo a suscriptores de ese chat
        messagingTemplate.convertAndSend("/topic/chat/" + chatId, savedMessage);
    }
}

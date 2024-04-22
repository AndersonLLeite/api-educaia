package com.api.educaia.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ChatRequestDTO {
    private String model;
    private List<MessageDTO> messages;

    public ChatRequestDTO(String model, List<MessageDTO> messages){
        this.model = model;
        this.messages  = messages;
    }
}

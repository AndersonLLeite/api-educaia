package com.api.educaia.controllers;

import com.api.educaia.dtos.ChatRequestDTO;
import com.api.educaia.dtos.ChatResponseDTO;
import com.api.educaia.dtos.MessageDTO;
import com.api.educaia.services.AssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/assistant")
public class assistantController {

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    /**
     * Creates a chat request and sends it to the OpenAI API
     * Returns the first message from the API response
     *
     * @param messages list of messages to send to the API
     * @return first message from the API response
     */
    @PostMapping("/chat")
    public String chat(@RequestBody List<MessageDTO> messages) {
        MessageDTO fixedMessage = new MessageDTO(
                "user",
                "dd"
        );
        messages.add(0, fixedMessage);
        ChatRequestDTO request = new ChatRequestDTO(model, messages);


        ChatResponseDTO response = restTemplate.postForObject(
                apiUrl,
                request,
                ChatResponseDTO.class);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }

        return response.getChoices().get(0).getMessage().getContent();
    }


}

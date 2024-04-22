package com.api.educaia.controllers;

import com.api.educaia.dtos.ChatRequestDTO;
import com.api.educaia.dtos.ChatResponseDTO;
import com.api.educaia.dtos.MessageDTO;
import com.api.educaia.dtos.QuizQuestionDTO;
import com.api.educaia.models.QuizQuestionModel;
import com.api.educaia.services.AssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.api.educaia.utils.QuizQuestionConverter.convertResponseToQuizQuestions;


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

    @PostMapping("/quiz")
    public ResponseEntity<?> quiz(@RequestBody String description) {
        ChatRequestDTO request = getChatRequestDTO(description);
        ChatResponseDTO response = restTemplate.postForObject(
                apiUrl,
                request,
                ChatResponseDTO.class);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        try {
            List<QuizQuestionDTO> quizQuestions = convertResponseToQuizQuestions(response.getChoices().get(0).getMessage().getContent());
            return ResponseEntity.ok(quizQuestions);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }


    }

    private ChatRequestDTO getChatRequestDTO(String description) {
        MessageDTO fixedMessage = new MessageDTO(
                "user",
                String.format("Gere 10 questões de quiz para o seguinte tema: %s. Inclua respostas erradas.\n" +
                                "Formate a resposta como JSON seguindo este formato de exemplo, sendo uma lista de objetos: \n" +
                                "[\n" +
                                "{\n" +
                                "    \"question\": \"Qual foi a participação do Japão na Segunda Guerra Mundial?\",\n" +
                                "    \"answers\": [\"O Japão não participou da Segunda Guerra Mundial\", \"O Japão foi um dos Aliados na guerra\", \"O Japão foi um dos países do Eixo na guerra\", \"O Japão teve uma atuação neutra na guerra\"],\n" +
                                "    \"correctAnswer\": 2\n" +
                                "}, \n" +
                                "]\n",

                        description)
        );

        List<MessageDTO> messages = new ArrayList<>();
        messages.add(0, fixedMessage);
        ChatRequestDTO request = new ChatRequestDTO(model, messages);
        return request;
    }



}

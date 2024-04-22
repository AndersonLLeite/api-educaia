package com.api.educaia.utils;

import com.api.educaia.dtos.QuizQuestionDTO;
import com.api.educaia.models.QuizQuestionModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class QuizQuestionConverter {

    public static List<QuizQuestionDTO> convertResponseToQuizQuestions(String jsonResponse) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Converte a string JSON em uma lista de objetos QuizQuestionModel
            List<QuizQuestionDTO> quizQuestions = objectMapper.readValue(
                    jsonResponse,
                    new TypeReference<List<QuizQuestionDTO>>() {
                    }
            );

            return quizQuestions;
        } catch (IOException e) {
            System.out.println("Erro ao converter JSON para QuizQuestionModel");
            return null;

        }
    }
}
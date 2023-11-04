package com.api.educaia.services;
import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Service
public class AssistantServiceImpl implements  AssistantService{

    private final Dotenv dotenv = Dotenv.load();
    private final String openAIAPIKey = dotenv.get("OPENAI_API_KEY");
    private List<JSONObject> messages = new ArrayList<>();
    private final String url = "https://api.openai.com/v1/engines/davinci/completions";
    @Override
    public String getChatGptResponse(String prompt) {
        messages.add(createMessage("user", prompt));
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + openAIAPIKey)
                .POST(HttpRequest.BodyPublishers.ofString(createRequestBody()))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONArray choices = new JSONObject(response.body())
                        .getJSONArray("choices");
                String assistantMessage = choices.getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content");

                assistantMessage = new String(assistantMessage.getBytes(), StandardCharsets.UTF_8);

                assistantMessage = assistantMessage.trim();

                messages.add(createMessage("assistant", assistantMessage));
                System.out.println(messages);
                return assistantMessage;
            } else {
                return "An internal error occurred";
            }
        } catch (IOException | InterruptedException | JSONException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    private JSONObject createMessage(String role, String content) {
        JSONObject message = new JSONObject();
        message.put("role", role);
        message.put("content", content);
        return message;
    }

    private String createRequestBody() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", messages);
        return requestBody.toString();
    }
}

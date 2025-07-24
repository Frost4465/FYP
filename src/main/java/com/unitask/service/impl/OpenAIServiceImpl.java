package com.unitask.service.impl;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import com.unitask.constant.OpenAIModel;
import com.unitask.dto.AIRequest;
import com.unitask.service.OpenAIService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final String ROLE_USER = "user";

    private final OpenAiService service;

    public OpenAIServiceImpl(@Value("${openAI.token}") String token) {
        this.service = new OpenAiService(token);
    }

    @Override
    public String getResponse(AIRequest aiRequest) {
        ChatMessage userMsg = new ChatMessage(ROLE_USER, aiRequest.getUserMessage());
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model(OpenAIModel.GPT_3_5_TURBO.getValue())
                .messages(List.of(userMsg))
                .maxTokens(150)
                .temperature(0.7)
                .build();
        return service.createChatCompletion(request)
                .getChoices()
                .get(0)
                .getMessage()
                .getContent();
    }
}

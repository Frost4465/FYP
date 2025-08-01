package com.unitask.controller;

import com.theokanning.openai.service.OpenAiService;
import com.unitask.dto.AIRequest;
import com.unitask.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/OpenAI")
public class OpenAIController {

    @Autowired
    private OpenAIService openAIService;


    @PostMapping("/chatbot")
    public ResponseEntity<?> callChatbot(@RequestPart AIRequest request) throws Exception {
        return ResponseEntity.ok().body(openAIService.getResponse(request));
    }


}

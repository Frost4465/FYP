package com.unitask.service;

import com.unitask.dto.AIRequest;

public interface OpenAIService {

    String getResponse(AIRequest request);

}

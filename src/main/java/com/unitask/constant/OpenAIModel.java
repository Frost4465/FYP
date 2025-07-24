package com.unitask.constant;

import lombok.Getter;

@Getter
public enum OpenAIModel {


    GPT_3_5_TURBO("gpt-3.5-turbo", "GPT-3.5-Turbo"),
    GPT_3_5_TURBO_0301("gpt-3.5-turbo-0301", "GPT-3.5-Turbo-0301"),
    GPT_4("gpt-4", "GPT-4"),
    GPT_4_0314("gpt-4-0314", "GPT-4-0314"),
    GPT_4_32K("gpt-4-32k", "GPT-4-32k"),
    GPT_4_32K_0314("gpt-4-32k-0314", "GPT-4-32k-0314"),
    GPT_4_1106_preview("gpt-4-1106-preview", "GPT-4-1106-Preview");


    private final String value;
    private final String name;

    OpenAIModel(String value, String name) {
        this.value = value;
        this.name = name;
    }

}

package com.asynccc.chatgpt.client.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public  class UsageDTO {
    @JsonProperty("prompt_tokens")
    private Integer promptTokens;
    @JsonProperty("completion_tokens")
    private Integer completionTokens;
    @JsonProperty("total_tokens")
    private Integer totalTokens;
}

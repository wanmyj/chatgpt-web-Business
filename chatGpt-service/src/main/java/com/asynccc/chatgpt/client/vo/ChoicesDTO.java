package com.asynccc.chatgpt.client.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public  class ChoicesDTO {
    @JsonProperty("message")
    private MessageDTO message;
    @JsonProperty("finish_reason")
    private String finishReason;
    @JsonProperty("index")
    private Integer index;
}

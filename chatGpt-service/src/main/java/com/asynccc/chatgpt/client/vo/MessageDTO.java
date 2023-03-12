package com.asynccc.chatgpt.client.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MessageDTO {
    @JsonProperty("role")
    private String role;
    @JsonProperty("content")
    private String content;
}
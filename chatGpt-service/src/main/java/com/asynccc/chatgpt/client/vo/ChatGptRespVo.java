package com.asynccc.chatgpt.client.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ChatGptRespVo {

    private String id;
    private String object;
    private Integer created;
    private String model;
    private UsageDTO usage;
    private List<ChoicesDTO> choices;




}

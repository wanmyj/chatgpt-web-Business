package com.asynccc.chatgpt.client.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ChatGptReqVo
{
    private String model;
    private List<MessageDTO> messages;
}

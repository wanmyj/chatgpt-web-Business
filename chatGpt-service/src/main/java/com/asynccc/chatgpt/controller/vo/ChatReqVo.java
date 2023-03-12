package com.asynccc.chatgpt.controller.vo;

import com.asynccc.chatgpt.client.vo.MessageDTO;
import com.asynccc.chatgpt.domain.TokenDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatReqVo
{
    private String token;

    private String question;
}

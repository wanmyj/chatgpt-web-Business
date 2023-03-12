package com.asynccc.chatgpt.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayReqVo
{
    private String fee;

    private String token;
}

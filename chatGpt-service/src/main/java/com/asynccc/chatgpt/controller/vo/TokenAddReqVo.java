package com.asynccc.chatgpt.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenAddReqVo
{
    private String name;

    private String phone;

    private String email;

    private String fee;
}

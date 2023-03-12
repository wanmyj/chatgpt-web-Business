package com.asynccc.chatgpt.controller;

import com.asynccc.chatgpt.client.vo.ChatGptRespVo;
import com.asynccc.chatgpt.common.ComResponse;
import com.asynccc.chatgpt.common.ResponseCode;
import com.asynccc.chatgpt.controller.vo.ChatReqVo;
import com.asynccc.chatgpt.controller.vo.ChatRespVo;
import com.asynccc.chatgpt.domain.TokenDomain;
import com.asynccc.chatgpt.entity.Token;
import com.asynccc.chatgpt.service.ChatService;
import com.asynccc.chatgpt.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    private final static Logger LOG = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private ChatService chatService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/chat")
    public ComResponse<ChatRespVo> chat(@RequestBody ChatReqVo reqVo) {
        ComResponse<ChatRespVo> response = new ComResponse<>();
        if (reqVo == null) {
            response.setCode(ResponseCode.INVALID_PARAM);
            response.setMsg("invalid param.");
            return response;
        }
        String token = reqVo.getToken();
        String question = reqVo.getQuestion();

        if (StringUtils.isEmpty(token)) {
            ChatRespVo chatRespVo = chatService.chatWithoutToken(question);
            response.setData(chatRespVo);
            return response;
        }

        Token one = tokenService.queryToken(token);
        if (one == null) {
            response.setCode(ResponseCode.ERROR);
            response.setMsg("该token不存在，请申请新token.");
            return response;
        }
        boolean check = tokenService.check(one, question.length() * 2);
        if (!check) {
            response.setCode(ResponseCode.ERROR);
            response.setMsg("此token预估剩余费用不足，请充值.");
            ChatRespVo chatRespVo = new ChatRespVo();
            chatRespVo.setTokenDomain(TokenDomain.getDomain(one));
            response.setData(chatRespVo);
            return response;
        }
        ChatRespVo chatRespVo = chatService.chatWithToken(one, question);
        response.setData(chatRespVo);
        return response;
    }
}

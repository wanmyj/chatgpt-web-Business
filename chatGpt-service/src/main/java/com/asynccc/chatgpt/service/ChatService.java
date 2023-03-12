package com.asynccc.chatgpt.service;

import cn.hutool.core.collection.CollectionUtil;
import com.asynccc.chatgpt.client.ChatGptClient;
import com.asynccc.chatgpt.client.vo.ChatGptRespVo;
import com.asynccc.chatgpt.client.vo.ChoicesDTO;
import com.asynccc.chatgpt.client.vo.MessageDTO;
import com.asynccc.chatgpt.client.vo.UsageDTO;
import com.asynccc.chatgpt.common.Constant;
import com.asynccc.chatgpt.domain.TokenDomain;
import com.asynccc.chatgpt.controller.vo.ChatRespVo;
import com.asynccc.chatgpt.entity.Token;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ChatService {
    private final static Logger LOG = LoggerFactory.getLogger(ChatService.class);

    @Autowired
    private ChatGptClient client;

    @Autowired
    private TokenService tokenService;

    public ChatRespVo chatWithToken(Token token, String question) {
        ChatRespVo chatRespVo = new ChatRespVo();

        ChatGptRespVo respVo = client.post(question);
        UsageDTO usage = respVo.getUsage();
        List<ChoicesDTO> choices = respVo.getChoices();
        MessageDTO messageDTO = null;
        if (!CollectionUtil.isEmpty(choices)) {
            messageDTO = choices.get(0).getMessage();
        }

        Integer totalTokens = usage.getTotalTokens();
        BigDecimal curFee = BigDecimal.valueOf(Constant.price * totalTokens);
        TokenDomain tokenDomain = tokenService.update(token, totalTokens,curFee,-1);
        chatRespVo.setTokenDomain(tokenDomain);
        chatRespVo.setMessageDTO(messageDTO);
        return chatRespVo;
    }

    public ChatRespVo chatWithoutToken(String question) {
        ChatRespVo chatRespVo = new ChatRespVo();
        try {
            Thread.sleep(300);

            ChatGptRespVo respVo = client.post(question);
            List<ChoicesDTO> choices = respVo.getChoices();

            MessageDTO messageDTO = null;
            if (!CollectionUtil.isEmpty(choices)) {
                messageDTO = choices.get(0).getMessage();
            }
            chatRespVo.setTokenDomain(null);
            chatRespVo.setMessageDTO(messageDTO);
        } catch (Exception e) {
            LOG.error("error: {}", e.getMessage());
        }

        return chatRespVo;
    }
}

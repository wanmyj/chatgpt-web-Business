package com.asynccc.chatgpt.client;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.asynccc.chatgpt.client.vo.ChatGptReqVo;
import com.asynccc.chatgpt.client.vo.ChatGptRespVo;
import com.asynccc.chatgpt.client.vo.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatGptClient {
    private final String ROLE = "user";
    private final String Model = "gpt-3.5-turbo";
    private final String TOKEN = "Bearer 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";

    private final String URL = "https://api.openai.com/v1/chat/completions";

    public ChatGptRespVo post(String question) {
        ChatGptReqVo chatGptReqVo = new ChatGptReqVo();
        chatGptReqVo.setModel(Model);

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setRole(ROLE);
        messageDTO.setContent(question);
        List<MessageDTO> list = new ArrayList<>();
        list.add(messageDTO);
        chatGptReqVo.setMessages(list);

        String reqBody = JSONUtil.toJsonPrettyStr(chatGptReqVo);

        HttpResponse response = HttpUtil.createPost(URL).header("Authorization", TOKEN)
                .header("Content-Type", "application/json").body(reqBody).execute();

        System.out.println(response);
        String res= response.body();
        return JSONUtil.toBean(res, ChatGptRespVo.class);
    }
}

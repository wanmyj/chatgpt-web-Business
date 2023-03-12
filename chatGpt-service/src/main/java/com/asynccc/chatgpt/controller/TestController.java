package com.asynccc.chatgpt.controller;

import com.asynccc.chatgpt.common.ComResponse;
import com.asynccc.chatgpt.common.ResponseCode;
import com.asynccc.chatgpt.controller.vo.ChatReqVo;
import com.asynccc.chatgpt.controller.vo.ChatRespVo;
import com.asynccc.chatgpt.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final static Logger LOG = LoggerFactory.getLogger(TestController.class);

    @PostMapping("/test")
    public ComResponse test() {
        LOG.info("test success");
        return new ComResponse<ChatRespVo>();
    }
}

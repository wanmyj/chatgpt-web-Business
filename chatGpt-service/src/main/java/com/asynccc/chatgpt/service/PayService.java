package com.asynccc.chatgpt.service;

import cn.hutool.core.collection.CollectionUtil;
import com.asynccc.chatgpt.client.ChatGptClient;
import com.asynccc.chatgpt.client.vo.ChatGptRespVo;
import com.asynccc.chatgpt.client.vo.ChoicesDTO;
import com.asynccc.chatgpt.client.vo.MessageDTO;
import com.asynccc.chatgpt.client.vo.UsageDTO;
import com.asynccc.chatgpt.controller.vo.ChatRespVo;
import com.asynccc.chatgpt.domain.PayDomain;
import com.asynccc.chatgpt.domain.TokenDomain;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PayService {
    private final static Logger LOG = LoggerFactory.getLogger(PayService.class);

    public Boolean pay(BigDecimal fee){

        return true;
    }

}

package com.asynccc.chatgpt.controller;

import com.asynccc.chatgpt.common.ComResponse;
import com.asynccc.chatgpt.common.ResponseCode;
import com.asynccc.chatgpt.controller.vo.TokenAddReqVo;
import com.asynccc.chatgpt.domain.TokenDomain;
import com.asynccc.chatgpt.service.PayService;
import com.asynccc.chatgpt.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController()
@RequestMapping("/token")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private PayService payService;

    @PostMapping("/add")
    public ComResponse add(@RequestBody TokenAddReqVo reqVo) {
        ComResponse<Object> response = new ComResponse<>();
        String fee = reqVo.getFee();
        if (reqVo==null || StringUtils.isEmpty(fee)){
            response.setCode(ResponseCode.INVALID_PARAM);
            response.setMsg("无效参数");
            return response;
        }

        BigDecimal decimal = new BigDecimal(fee);
        if(!payService.pay(decimal)){
            response.setCode(ResponseCode.ERROR);
            response.setMsg("支付失败");
            return response;
        }

        TokenDomain domain = tokenService.add(reqVo.getName(), reqVo.getPhone(), reqVo.getEmail(), decimal);

        response.setData(domain);
        return response;
    }

    @PostMapping("/query")
    public ComResponse query(@RequestParam String token) {
        ComResponse<Object> response = new ComResponse<>();
        TokenDomain domain = tokenService.query(token);
        if (domain == null) {
            response.setCode(ResponseCode.ERROR);
            response.setMsg("无此token");
            return response;
        }
        response.setData(domain);
        return response;
    }

    @PostMapping("/delete")
    public ComResponse delete(@RequestParam String token) {
        ComResponse<Object> response = new ComResponse<>();
        Boolean delete = tokenService.delete(token);
        if (!delete) {
            response.setCode(ResponseCode.ERROR);
            response.setMsg("删除失败");
            return response;
        }
        return response;
    }
}

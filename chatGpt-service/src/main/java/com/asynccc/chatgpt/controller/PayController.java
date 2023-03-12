package com.asynccc.chatgpt.controller;

import com.asynccc.chatgpt.common.ComResponse;
import com.asynccc.chatgpt.common.Constant;
import com.asynccc.chatgpt.common.ResponseCode;
import com.asynccc.chatgpt.controller.vo.PayReqVo;
import com.asynccc.chatgpt.controller.vo.TokenAddReqVo;
import com.asynccc.chatgpt.domain.TokenDomain;
import com.asynccc.chatgpt.entity.Token;
import com.asynccc.chatgpt.service.PayService;
import com.asynccc.chatgpt.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.MathContext;

@RestController()
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private PayService payService;

    @PostMapping()
    public ComResponse<TokenDomain> pay(@RequestBody PayReqVo reqVo) {
        ComResponse<TokenDomain> response = new ComResponse<>();
        if (reqVo == null || StringUtils.isEmpty(reqVo.getFee())) {
            response.setCode(ResponseCode.INVALID_PARAM);
            response.setMsg("无效参数");
            return response;
        }
        String fee = reqVo.getFee();
        String token = reqVo.getToken();
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(fee)) {
            response.setCode(ResponseCode.INVALID_PARAM);
            response.setMsg("无效参数");
            return response;
        }

        Token one = tokenService.queryToken(token);
        if (one == null) {
            response.setCode(ResponseCode.INVALID_PARAM);
            response.setMsg("该token不存在，请申请新token.");
            return response;
        }

        BigDecimal decimal = new BigDecimal(fee);
        if (!payService.pay(decimal)) {
            response.setCode(ResponseCode.ERROR);
            response.setMsg("支付失败");
            return response;
        }
        BigDecimal divide = decimal.divide(new BigDecimal(Constant.price), MathContext.DECIMAL32);
        TokenDomain domain = tokenService.update(one, divide.intValue(), decimal, 1);

        response.setData(domain);
        return response;
    }
}

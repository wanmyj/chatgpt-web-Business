package com.asynccc.chatgpt.domain;

import com.asynccc.chatgpt.entity.Token;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TokenDomain {
    private String token;
    private BigDecimal fee;

    private BigDecimal remainFee;
    private Long remainCount;

    private Date overtime;

    public static TokenDomain getDomain(Token one) {

        TokenDomain domain = new TokenDomain();
        domain.setFee(one.getFee());
        domain.setRemainFee(one.getRemainFee());
        domain.setRemainCount(one.getRemainCount());
        domain.setOvertime(one.getOverTime());
        domain.setToken(one.getToken());

        return domain;
    }
}

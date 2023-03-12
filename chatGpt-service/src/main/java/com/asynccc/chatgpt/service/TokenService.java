package com.asynccc.chatgpt.service;

import com.asynccc.chatgpt.common.Constant;
import com.asynccc.chatgpt.domain.TokenDomain;
import com.asynccc.chatgpt.entity.Token;
import com.asynccc.chatgpt.mapper.TokenMapper;
import com.asynccc.chatgpt.util.PasswordUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Service
public class TokenService {
    public final static Long EFFECT_DAY_COUNT = 180 * 24 * 60 * 60 * 1000L;

    @Autowired
    private TokenMapper mapper;

    public TokenDomain add(String name, String phone, String email, BigDecimal fee) {
        long now = System.currentTimeMillis();

        String tokenValue = PasswordUtil.encrypt(now);

        Double count = (fee.doubleValue() / Constant.price);
        long value = count.longValue();

        Token token = new Token();
        token.setToken(tokenValue);
        token.setName(name);
        token.setPhone(phone);
        token.setEmail(email);
        token.setFee(fee);
        token.setRemainFee(fee);
        token.setTotalCount(value);
        token.setRemainCount(value);
        token.setCreateTime(new Date(now));
        token.setOverTime(new Date(now + EFFECT_DAY_COUNT));
        token.setDeleted(0);

        mapper.insert(token);

        return TokenDomain.getDomain(token);
    }

    public TokenDomain query(String token) {
        QueryWrapper<Token> wrapper = new QueryWrapper<>();
        wrapper.eq("token", token);

        Token one = mapper.selectOne(wrapper);
        if (one == null || one.getDeleted() == 1) {
            return null;
        }

        return TokenDomain.getDomain(one);
    }

    public Token queryToken(String token) {
        QueryWrapper<Token> wrapper = new QueryWrapper<>();
        wrapper.eq("token", token);

        Token one = mapper.selectOne(wrapper);
        if (one == null || one.getDeleted() == 1) {
            return null;
        }

        return one;
    }

    public TokenDomain update(Token one, Integer count, BigDecimal curFee, int operator) {
        Long remainCount = one.getRemainCount();
        BigDecimal remainFee = one.getRemainFee();
        BigDecimal fee = one.getFee();
        Long totalCount = one.getTotalCount();

        if (operator < 0) {
            long remainC = remainCount - count;
            BigDecimal remainF = remainFee.subtract(curFee);
            one.setRemainFee(remainF);
            one.setRemainCount(remainC);
        } else {
            long remainC = remainCount + count;
            BigDecimal remainF = remainFee.add(curFee);
            BigDecimal newTotal = fee.add(curFee);
            long newTotalCount = totalCount + count;

            one.setRemainFee(remainF);
            one.setRemainCount(remainC);
            one.setFee(newTotal);
            one.setTotalCount(newTotalCount);
        }

        one.setUpdateTime(new Date());

        mapper.updateById(one);

        return TokenDomain.getDomain(one);
    }

    public boolean check(Token one, Integer count) {
        if (one == null) {
            return false;
        }

        Long remainCount = one.getRemainCount();
        if (remainCount < Constant.MIN_REMAIN_COUNT) {
            return false;
        }
        return remainCount.compareTo(count.longValue()) >= 0;
    }

    public Boolean delete(String token) {
        QueryWrapper<Token> wrapper = new QueryWrapper<>();
        wrapper.eq("token", token);

        Token one = mapper.selectOne(wrapper);
        one.setDeleted(1);

        int count = mapper.updateById(one);
        return count == 1;
    }
}

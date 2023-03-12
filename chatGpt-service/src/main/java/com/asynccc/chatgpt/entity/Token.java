package com.asynccc.chatgpt.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Token {
    private Long id;
    private String token;
    private String name;
    private String phone;
    private String email;
    private BigDecimal fee;
    private BigDecimal remainFee;
    private Long totalCount;

    private Long remainCount;

    private Date createTime;
    private Date updateTime;
    private Date overTime;
    private Integer deleted;
}

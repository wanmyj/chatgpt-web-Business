package com.asynccc.chatgpt.domain;

import com.asynccc.chatgpt.entity.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayDomain {
    private BigDecimal fee;
    private Boolean success;
}

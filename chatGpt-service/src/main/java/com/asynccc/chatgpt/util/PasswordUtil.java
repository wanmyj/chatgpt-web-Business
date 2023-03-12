package com.asynccc.chatgpt.util;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;

public class PasswordUtil {
    /**
     * 加密（加盐处理）
     *
     * @param password
     *         待加密密码（需要加密的密码）
     * @return 加密后的密码
     */
    public static String encrypt(Long password) {
        // 随机盐值 UUID
        Long salt = System.currentTimeMillis() / 1000000;
        // 密码=md5(随机盐值+密码)
        byte[] digest = DigestUtils.md5Digest((salt + "$" + password).getBytes());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            int val = ((int) digest[i]) & 0xff;
            if (val < 16)
                builder.append("0");
            builder.append(Integer.toHexString(val));
        }

        return salt + builder.toString();
    }
}

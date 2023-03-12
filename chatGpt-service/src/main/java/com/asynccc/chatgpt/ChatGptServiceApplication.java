package com.asynccc.chatgpt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.asynccc.chatgpt.mapper")
public class ChatGptServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatGptServiceApplication.class, args);
    }

}

package com.asynccc.chatgpt.common;

import com.alibaba.druid.filter.AutoLoad;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComResponse<T> {
    private int code = 200;
    private String msg = "操作成功";
    private T data;
    private long cost;
}

package com.example.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class JsonUtils {
    static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将对象转JSON字符串
     *
     * @param object 对象
     * @return JSON字符串
     */
    @SneakyThrows
    public static String toJsonStr(Object object) {
        return objectMapper.writeValueAsString(object);
    }
}

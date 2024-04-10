package com.example.common.exception;

import cn.hutool.jwt.JWTException;
import com.example.common.domain.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public R handler(RuntimeException e) {
        log.error("运行时异常：{}", e.getMessage());
        return R.error(e.getMessage());
    }

    @ExceptionHandler(value = JWTException.class)
    public R handler(JWTException e) {
        log.error("Token过期：{}", e.getMessage());
        return R.error(e.getMessage());
    }
}

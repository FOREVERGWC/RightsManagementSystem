package com.example.common.exception;

import cn.hutool.jwt.JWTException;
import com.example.common.domain.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String message = errors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return R.error(message);
    }
}

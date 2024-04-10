package com.example.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

/**
 * 业务异常
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -4715391447646124395L;
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误消息
     */
    private String msg;

    public ServiceException(String msg) {
        this.msg = msg;
    }
}

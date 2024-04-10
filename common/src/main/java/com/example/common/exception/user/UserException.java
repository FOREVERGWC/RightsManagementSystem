package com.example.common.exception.user;

import com.example.common.exception.BaseException;

import java.io.Serial;

/**
 * 用户信息异常类
 */
public class UserException extends BaseException {
    @Serial
    private static final long serialVersionUID = 4965060239854795744L;

    public UserException(Object[] args, String code) {
        super("user", args, code, null);
    }
}

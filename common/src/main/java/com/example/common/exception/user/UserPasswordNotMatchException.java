package com.example.common.exception.user;

import java.io.Serial;

/**
 * 密码长度不符合规范异常类
 */
public class UserPasswordNotMatchException extends UserException {
    @Serial
    private static final long serialVersionUID = -3957951034774742622L;

    public UserPasswordNotMatchException() {
        super(null, "user.password.not.match");
    }
}

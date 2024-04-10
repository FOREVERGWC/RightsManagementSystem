package com.example.common.exception.user;

import java.io.Serial;

/**
 * 用户禁用注册异常
 */
public class UserDisableRegisterException extends UserException {
    @Serial
    private static final long serialVersionUID = 4283569839345436646L;

    public UserDisableRegisterException() {
        super(null, "user.register.disable");
    }
}

package com.example.common.exception.user;

import java.io.Serial;

/**
 * 用户名长度不符合规范异常类
 */
public class UserUsernameNotMatchException extends UserException {
    @Serial
    private static final long serialVersionUID = -8268165656488897363L;

    public UserUsernameNotMatchException() {
        super(null, "user.username.not.match");
    }
}

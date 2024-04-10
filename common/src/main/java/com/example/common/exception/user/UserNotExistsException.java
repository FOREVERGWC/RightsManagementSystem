package com.example.common.exception.user;

import java.io.Serial;

/**
 * 用户不存在异常类
 */
public class UserNotExistsException extends UserException {
    @Serial
    private static final long serialVersionUID = -4821042510758263450L;

    public UserNotExistsException() {
        super(null, "user.not.exists");
    }
}

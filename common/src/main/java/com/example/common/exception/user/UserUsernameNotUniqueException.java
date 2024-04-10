package com.example.common.exception.user;

import java.io.Serial;

/**
 * 用户名已存在异常类
 */
public class UserUsernameNotUniqueException extends UserException {
    @Serial
    private static final long serialVersionUID = -5097114665157971792L;

    public UserUsernameNotUniqueException() {
        super(null, "user.username.not.unique");
    }
}

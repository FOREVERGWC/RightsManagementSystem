package com.example.common.exception.user;

import java.io.Serial;

/**
 * 用户注册失败异常类
 */
public class UserRegisterFailedException extends UserException {
    @Serial
    private static final long serialVersionUID = -6662669503154063336L;

    public UserRegisterFailedException() {
        super(null, "user.register.failed");
    }
}

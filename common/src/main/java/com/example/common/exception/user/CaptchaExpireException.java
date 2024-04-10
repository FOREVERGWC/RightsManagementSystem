package com.example.common.exception.user;

import java.io.Serial;

/**
 * 验证码失效异常类
 */
public class CaptchaExpireException extends UserException {
    @Serial
    private static final long serialVersionUID = -4753662265727058711L;

    public CaptchaExpireException() {
        super(null, "user.captcha.expire");
    }
}

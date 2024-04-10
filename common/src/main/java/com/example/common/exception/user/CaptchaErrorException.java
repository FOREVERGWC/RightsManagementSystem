package com.example.common.exception.user;

import java.io.Serial;

/**
 * 验证码错误异常类
 */
public class CaptchaErrorException extends UserException {
    @Serial
    private static final long serialVersionUID = -167404976269303060L;

    public CaptchaErrorException() {
        super(null, "user.captcha.error");
    }
}

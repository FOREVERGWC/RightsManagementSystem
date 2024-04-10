package com.example.common.exception;

import org.springframework.security.core.AuthenticationException;

import java.io.Serial;

public class UserCountLockException extends AuthenticationException {
    @Serial
    private static final long serialVersionUID = 5539965883404008561L;

    public UserCountLockException(String message) {
        super(message);
    }
}

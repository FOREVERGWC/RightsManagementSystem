package com.example.system.context;

import org.springframework.security.core.Authentication;

/**
 * 身份验证信息
 */
public class AuthenticationContextHolder {
    private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal<>();

    public static Authentication getContextHolder() {
        return contextHolder.get();
    }

    public static void setContextHolder(Authentication context) {
        contextHolder.set(context);
    }

    public static void clearContextHolder() {
        contextHolder.remove();
    }
}

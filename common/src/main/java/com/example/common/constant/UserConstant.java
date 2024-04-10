package com.example.common.constant;

public class UserConstant {
    /**
     * 用户状态正常
     */
    public static final Integer STATUS_OK = 0;
    /**
     * 用户状态禁用
     */
    public static final Integer STATUS_DISABLE = 1;
    /**
     * 用户名长度最小限制
     */
    public static final Integer USERNAME_MIN_LENGTH = 2;
    /**
     * 用户名长度最大限制
     */
    public static final Integer USERNAME_MAX_LENGTH = 20;
    /**
     * 密码长度最小限制
     */
    public static final Integer PASSWORD_MIN_LENGTH = 5;
    /**
     * 密码长度最大限制
     */
    public static final Integer PASSWORD_MAX_LENGTH = 20;
    /**
     * 令牌
     */
    public static final String TOKEN = "authorization";
    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";
    /**
     * JWT唯一标识
     */
    public static final String JWT_UUID = "uuid";
}

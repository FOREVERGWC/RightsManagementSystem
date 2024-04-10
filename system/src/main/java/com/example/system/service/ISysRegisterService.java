package com.example.system.service;

public interface ISysRegisterService {
    /**
     * 用户注册
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     */
    void register(String username, String password, String code, String uuid);
}

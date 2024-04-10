package com.example.system.service.impl;

import com.example.system.domain.model.LoginUser;
import com.example.system.service.ISysTokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 令牌 服务实现类
 * </p>
 */
@Service
public class SysTokenServiceImpl implements ISysTokenService {
    @Override
    public String createToken(LoginUser loginUser) {
        return null;
    }

    @Override
    public LoginUser getLoginUser(HttpServletRequest request) {
        return null;
    }

    @Override
    public void verifyToken(LoginUser user) {

    }

    @Override
    public void delLoginUser(String token) {

    }
}

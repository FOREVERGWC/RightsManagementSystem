package com.example.system.security.handler;

import com.example.common.utils.JsonUtils;
import com.example.common.utils.ServletUtils;
import com.example.common.domain.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        ServletUtils.write(response, JsonUtils.toJsonStr(R.error(HttpServletResponse.SC_UNAUTHORIZED, "认证失败，请登录！")));
    }
}

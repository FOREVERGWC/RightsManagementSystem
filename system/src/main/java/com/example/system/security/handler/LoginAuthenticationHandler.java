package com.example.system.security.handler;

import com.example.common.utils.JsonUtils;
import com.example.common.utils.ServletUtils;
import com.example.common.domain.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class LoginAuthenticationHandler implements AuthenticationEntryPoint {
    /**
     * 认证失败处理
     *
     * @param request       请求对象
     * @param response      响应对象
     * @param authException 认证异常
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        ServletUtils.write(response, JsonUtils.toJsonStr(R.error(HttpServletResponse.SC_UNAUTHORIZED, "认证失败，请登录！")));
    }
}

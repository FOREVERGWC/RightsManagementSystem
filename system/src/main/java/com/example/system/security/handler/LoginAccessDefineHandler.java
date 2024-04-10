package com.example.system.security.handler;

import com.example.common.utils.JsonUtils;
import com.example.common.utils.ServletUtils;
import com.example.common.domain.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginAccessDefineHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        ServletUtils.write(response, JsonUtils.toJsonStr(R.error(HttpServletResponse.SC_UNAUTHORIZED, "无权限，请联系管理员！")));
    }
}

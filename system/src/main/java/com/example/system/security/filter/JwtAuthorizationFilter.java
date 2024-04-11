package com.example.system.security.filter;

import cn.hutool.extra.spring.SpringUtil;
import com.example.common.utils.UserUtils;
import com.example.common.domain.model.LoginUser;
import com.example.system.service.ISysTokenService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {
//    @Resource
//    private ISysTokenService sysTokenService;
    ISysTokenService sysTokenService = SpringUtil.getBean(ISysTokenService.class);

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {
        log.error("路径：{}", request.getRequestURL());
        LoginUser user = sysTokenService.getLoginUser(request);
        Authentication authentication = UserUtils.getAuthentication();
        if (user != null && authentication == null) {
            sysTokenService.verifyToken(user);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}

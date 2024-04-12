package com.example.common.utils;

import com.example.common.constant.HttpConstant;
import com.example.common.domain.model.LoginUser;
import com.example.common.exception.ServiceException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {
    /**
     * 获取授权信息
     *
     * @return 授权信息
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前用户
     *
     * @return 用户
     */
    public static LoginUser getLoginUser() {
        try {
            return (LoginUser) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new ServiceException(HttpConstant.UNAUTHORIZED, "获取用户信息异常");
        }
    }

    /**
     * 获取当前用户ID
     *
     * @return 用户ID
     */
    public static Long getLoginUserId() {
        try {
            return getLoginUser().getUserId();
        } catch (Exception e) {
            throw new ServiceException(HttpConstant.UNAUTHORIZED, "获取用户信息异常");
        }
    }

    /**
     * 获取当前用户名
     *
     * @return 用户名
     */
    public static String getLoginUsername() {
        Authentication authentication = getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        return authentication.getName();
    }
}

package com.example.common.utils;

import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.http.Header;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ServletUtils {
    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 获取请求对象
     *
     * @return 请求对象
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取用户浏览器信息
     *
     * @return 用户浏览器信息
     */
    public static UserAgent getUserAgent() {
        return UserAgentUtil.parse(getRequest().getHeader(Header.USER_AGENT.getValue()));
    }

    /**
     * 获取用户浏览器信息
     *
     * @param request 请求对象
     * @return 用户浏览器信息
     */
    public static UserAgent getUserAgent(HttpServletRequest request) {
        return UserAgentUtil.parse(request.getHeader(Header.USER_AGENT.getValue()));
    }

    /**
     * 获取用户IP
     *
     * @return 用户IP
     */
    public static String getUserIp() {
        return JakartaServletUtil.getClientIP(getRequest());
    }

    /**
     * 写入消息到响应对象
     *
     * @param response 响应对象
     * @param text     消息
     */
    public static void write(HttpServletResponse response, String text) {
        JakartaServletUtil.write(response, text, MediaType.APPLICATION_JSON_VALUE);
    }
}

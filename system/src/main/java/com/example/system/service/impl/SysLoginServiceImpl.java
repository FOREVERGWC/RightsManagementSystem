package com.example.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.example.common.constant.CacheConstant;
import com.example.common.constant.Constant;
import com.example.common.constant.UserConstant;
import com.example.common.domain.model.LoginUser;
import com.example.common.exception.ServiceException;
import com.example.common.exception.user.*;
import com.example.common.utils.MessageUtils;
import com.example.common.utils.RedisUtils;
import com.example.system.context.AuthenticationContextHolder;
import com.example.system.service.ISysLoginInfoService;
import com.example.system.service.ISysLoginService;
import com.example.system.service.ISysTokenService;
import com.example.system.service.ISysUserService;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysLoginServiceImpl implements ISysLoginService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private ISysLoginInfoService sysLoginInfoService;
    @Resource
    private ISysUserService sysUserService;
    @Resource
    private ISysTokenService sysTokenService;
    @Resource
    private RedisUtils redisUtils;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String login(String username, String password, String code, String uuid) {
        validateCaptcha(username, code, uuid); // 校验验证码
        loginPreCheck(username, password); // 密码前置校验
        Authentication authentication;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContextHolder(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                sysLoginInfoService.recordSysLoginInfo(username, Constant.LOGIN_FAIL, MessageUtils.getMsg("user.not.exists"));
                throw new UserNotExistsException();
            } else {
                sysLoginInfoService.recordSysLoginInfo(username, Constant.LOGIN_FAIL, e.getMessage());
                throw new ServiceException(e.getMessage());
            }
        } finally {
            AuthenticationContextHolder.clearContextHolder();
        }
        sysLoginInfoService.recordSysLoginInfo(username, Constant.LOGIN_SUCCESS, MessageUtils.getMsg("user.login.success"));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        sysUserService.recordLoginInfo(loginUser.getUserId());
        return sysTokenService.createToken(loginUser);
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     */
    private void validateCaptcha(String username, String code, String uuid) {
        // TODO: 2024/1/3 获取验证码开关，异步写入错误消息
        boolean captchaEnabled = false; // 获取验证码开关
        if (!captchaEnabled) {
            return;
        }
        // 校验Redis验证码
        String verifyKey = CacheConstant.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisUtils.getCacheObject(verifyKey);
        redisUtils.deleteObject(verifyKey);
        if (captcha == null) {
            sysLoginInfoService.recordSysLoginInfo(username, Constant.LOGIN_FAIL, MessageUtils.getMsg("user.captcha.expire"));
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            sysLoginInfoService.recordSysLoginInfo(username, Constant.LOGIN_FAIL, MessageUtils.getMsg("user.captcha.error"));
            throw new CaptchaErrorException();
        }
    }

    /**
     * 登录前置校验
     *
     * @param username 用户名
     * @param password 密码
     */
    private void loginPreCheck(String username, String password) {
        // 用户名或密码为空
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            sysLoginInfoService.recordSysLoginInfo(username, Constant.LOGIN_FAIL, MessageUtils.getMsg("not.null"));
            throw new UserNotExistsException();
        }
        // 用户名不在指定范围内
        if (username.length() < UserConstant.USERNAME_MIN_LENGTH || username.length() > UserConstant.USERNAME_MAX_LENGTH) {
            sysLoginInfoService.recordSysLoginInfo(username, Constant.LOGIN_FAIL, MessageUtils.getMsg("user.username.not.match"));
            throw new UserUsernameNotMatchException();
        }
        // 密码如果不在指定范围内
        if (password.length() < UserConstant.PASSWORD_MIN_LENGTH || password.length() > UserConstant.PASSWORD_MAX_LENGTH) {
            sysLoginInfoService.recordSysLoginInfo(username, Constant.LOGIN_FAIL, MessageUtils.getMsg("user.password.not.match"));
            throw new UserPasswordNotMatchException();
        }
        // IP黑名单校验
        // TODO: 2023/12/28 IP黑名单校验
    }
}

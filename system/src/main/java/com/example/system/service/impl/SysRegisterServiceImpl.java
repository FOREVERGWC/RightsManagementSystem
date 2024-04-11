package com.example.system.service.impl;

import com.example.common.constant.CacheConstant;
import com.example.common.constant.Constant;
import com.example.common.exception.user.CaptchaErrorException;
import com.example.common.exception.user.CaptchaExpireException;
import com.example.common.exception.user.UserRegisterFailedException;
import com.example.common.exception.user.UserUsernameNotUniqueException;
import com.example.common.utils.MessageUtils;
import com.example.common.utils.RedisUtils;
import com.example.common.utils.SecurityUtils;
import com.example.common.utils.UserUtils;
import com.example.common.domain.entity.SysUser;
import com.example.system.service.ISysLoginInfoService;
import com.example.system.service.ISysRegisterService;
import com.example.system.service.ISysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SysRegisterServiceImpl implements ISysRegisterService {
    @Resource
    private ISysUserService sysUserService;
    @Resource
    private ISysLoginInfoService sysLoginInfoService;
    @Resource
    private RedisUtils redisUtils;

    @Override
    public void register(String username, String password, String code, String uuid) {
        validateCaptcha(username, code, uuid);
        registerPreCheck(username);
        SysUser sysUser = SysUser.builder() //
                .username(username) //
                .password(SecurityUtils.encryptPassword(password)) //
                .nickname(username) //
                .loginIp("") //
                .createBy(UserUtils.getLoginUsername() != null ? UserUtils.getLoginUsername() : username) //
                .remark("") //
                .build();
        if (sysUserService.save(sysUser)) {
            sysLoginInfoService.recordSysLoginInfo(username, Constant.REGISTER_SUCCESS, username + MessageUtils.getMsg("user.register.success"));
            // TODO: 2024/4/10 异步记录日志
        } else {
            throw new UserRegisterFailedException();
        }
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     */
    private void validateCaptcha(String username, String code, String uuid) {
        // 验证码开关
        // TODO: 2024/4/10 验证码开关
        boolean captchaEnabled = false;
        if (!captchaEnabled) {
            return;
        }
        String verifyKey = CacheConstant.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisUtils.getCacheObject(verifyKey);
        redisUtils.deleteObject(verifyKey);
        if (captcha == null) {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            throw new CaptchaErrorException();
        }
    }

    /**
     * 注册前置校验
     *
     * @param username 用户名
     */
    private void registerPreCheck(String username) {
        // IP黑名单校验
        // TODO: 2023/12/28 IP黑名单校验
        if (!sysUserService.checkUsernameUnique(username)) {
            throw new UserUsernameNotUniqueException();
        }
    }
}

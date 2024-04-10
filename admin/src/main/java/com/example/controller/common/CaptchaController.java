package com.example.controller.common;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.UUID;
import com.example.common.constant.CacheConstant;
import com.example.common.domain.R;
import com.example.common.utils.RedisUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class CaptchaController {
    @Resource
    private RedisUtils redisUtils;

    /**
     * 生成验证码
     *
     * @return 验证码
     */
    @GetMapping("/captcha")
    public R getCaptcha() {
        // TODO: 2024/1/3 获取验证码开关
        boolean captchaEnabled = true; // 获取验证码开关
        if (!captchaEnabled) {
            return R.success().put("captchaEnabled", captchaEnabled);
        }
        String uuid = UUID.fastUUID().toString();
        String verifyKey = CacheConstant.CAPTCHA_CODE_KEY + uuid;
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 30);
        String code = lineCaptcha.getCode();
        redisUtils.setCacheObject(verifyKey, code, CacheConstant.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        String img = lineCaptcha.getImageBase64Data();
        log.info("验证码：{}", code);
        return R.success() //
                .put("captchaEnabled", captchaEnabled) //
                .put("uuid", uuid) //
                .put("img", img);
    }
}

package com.example.controller.system;

import com.example.common.constant.UserConstant;
import com.example.common.domain.R;
import com.example.system.domain.model.LoginBody;
import com.example.system.service.ISysLoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysLoginController {
    @Resource
    private ISysLoginService sysLoginService;

    /**
     * 登录
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public R login(@RequestBody LoginBody loginBody) {
        String authorization = sysLoginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(), loginBody.getUuid());
        return R.success().put(UserConstant.TOKEN, authorization);
    }
}

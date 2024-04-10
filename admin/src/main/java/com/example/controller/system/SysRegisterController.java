package com.example.controller.system;

import com.example.common.domain.R;
import com.example.common.exception.user.UserDisableRegisterException;
import com.example.system.domain.model.RegisterBody;
import com.example.system.service.ISysRegisterService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysRegisterController {
    @Resource
    private ISysRegisterService sysRegisterService;

    @PostMapping("/register")
    public R register(@Valid @RequestBody RegisterBody registerBody) {
        String registerEnabled = "true"; // 获取注册开关
        if (!("true".equals(registerEnabled))) {
            throw new UserDisableRegisterException();
        }
        sysRegisterService.register(registerBody.getUsername(), registerBody.getPassword(), registerBody.getCode(), registerBody.getUuid());
        return R.success();
    }
}

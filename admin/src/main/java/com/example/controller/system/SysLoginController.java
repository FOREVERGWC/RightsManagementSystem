package com.example.controller.system;

import com.example.common.constant.UserConstant;
import com.example.common.domain.R;
import com.example.common.domain.entity.SysUser;
import com.example.common.utils.UserUtils;
import com.example.system.domain.model.LoginBody;
import com.example.system.service.ISysLoginService;
import com.example.system.service.ISysPermissionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class SysLoginController {
    @Resource
    private ISysLoginService sysLoginService;
    @Resource
    private ISysPermissionService sysPermissionService;

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

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/info")
    public R getInfo() {
        SysUser sysUser = UserUtils.getLoginUser().getUser();
        Set<String> roles = sysPermissionService.getRolePermission(sysUser); // 角色集合
        Set<String> permissions = sysPermissionService.getMenuPermission(sysUser); // 权限集合
        return R.success() //
                .put("user", sysUser) //
                .put("roles", roles) //
                .put("permissions", permissions);
    }
}

package com.example.controller.system;

import com.example.common.annotation.Log;
import com.example.common.constant.UserConstant;
import com.example.common.domain.R;
import com.example.common.domain.entity.SysUser;
import com.example.common.domain.vo.RouterVo;
import com.example.common.enums.BusinessType;
import com.example.common.utils.UserUtils;
import com.example.system.domain.entity.SysMenu;
import com.example.system.domain.model.LoginBody;
import com.example.system.service.ISysLoginService;
import com.example.system.service.ISysMenuService;
import com.example.system.service.ISysPermissionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class SysLoginController {
    @Resource
    private ISysLoginService sysLoginService;
    @Resource
    private ISysPermissionService sysPermissionService;
    @Resource
    private ISysMenuService sysMenuService;

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
    @Log(title = "获取信息")
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

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("/routers")
    public R getRouters() {
        Long userId = UserUtils.getLoginUserId();
        List<SysMenu> menus = sysMenuService.getMenusTreeByUserId(userId);
        List<RouterVo> routers = sysMenuService.buildMenus(menus);
        return R.success().put("routers", routers);
    }
}

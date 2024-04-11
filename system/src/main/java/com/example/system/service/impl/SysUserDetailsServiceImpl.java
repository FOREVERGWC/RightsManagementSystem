package com.example.system.service.impl;

import com.example.common.constant.UserConstant;
import com.example.common.exception.UserCountLockException;
import com.example.common.utils.MessageUtils;
import com.example.system.domain.entity.SysUser;
import com.example.system.domain.model.LoginUser;
import com.example.system.service.ISysPermissionService;
import com.example.system.service.ISysUserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SysUserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private ISysUserService sysUserService;
    @Resource
    private ISysPermissionService sysPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException(MessageUtils.getMsg("user.not.exists"));
        }
        if (Objects.equals(sysUser.getStatus(), UserConstant.STATUS_DISABLE)) {
            throw new UserCountLockException(MessageUtils.getMsg("user.blocked"));
        }
        // TODO: 2024/1/1 登录账户密码错误次数缓存键名
        return new LoginUser(sysUser.getId(), sysUser.getDeptId(), sysUser, sysPermissionService.getMenuPerms(sysUser));
    }
}

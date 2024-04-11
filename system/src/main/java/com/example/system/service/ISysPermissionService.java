package com.example.system.service;

import com.example.system.domain.entity.SysUser;

import java.util.Set;

public interface ISysPermissionService {
    /**
     * 获取用户菜单权限
     *
     * @param sysUser 用户对象
     * @return 菜单权限信息
     */
    Set<String> getMenuPerms(SysUser sysUser);
}

package com.example.system.service.impl;

import com.example.common.domain.entity.SysRole;
import com.example.common.domain.entity.SysUser;
import com.example.system.service.ISysMenuService;
import com.example.system.service.ISysPermissionService;
import com.example.system.service.ISysRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class SysPermissionServiceImpl implements ISysPermissionService {
    @Resource
    private ISysMenuService sysMenuService;
    @Resource
    private ISysRoleService sysRoleService;

    @Override
    public Set<String> getMenuPerms(SysUser sysUser) {
        List<SysRole> sysRoles = sysUser.getRoles();
        List<Long> roleIds = sysRoles.stream().map(SysRole::getId).toList();
        return sysMenuService.getPermsByRoleIds(roleIds);
    }

    @Override
    public Set<String> getRolePermission(SysUser sysUser) {
        Set<String> roles = new HashSet<>();
        if (sysUser.getId() != null && Objects.equals(sysUser.getId(), 1L)) {
            roles.add("admin");
        } else {
            Set<String> perms = sysRoleService.getRolePermsByUserId(sysUser.getId());
            roles.addAll(perms);
        }
        return roles;
    }

    @Override
    public Set<String> getMenuPermission(SysUser sysUser) {
        return null;
    }
}

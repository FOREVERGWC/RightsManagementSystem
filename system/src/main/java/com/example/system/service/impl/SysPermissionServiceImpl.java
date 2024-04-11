package com.example.system.service.impl;

import com.example.system.domain.entity.SysRole;
import com.example.system.domain.entity.SysUser;
import com.example.system.service.ISysMenuService;
import com.example.system.service.ISysPermissionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SysPermissionServiceImpl implements ISysPermissionService {
    @Resource
    private ISysMenuService sysMenuService;

    @Override
    public Set<String> getMenuPerms(SysUser sysUser) {
        List<SysRole> sysRoles = sysUser.getRoles();
        List<Long> roleIds = sysRoles.stream().map(SysRole::getId).toList();
        return sysMenuService.getPermsByRoleIds(roleIds);
    }
}

package com.example.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.domain.entity.SysRole;

import java.util.Set;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 */
public interface ISysRoleService extends IService<SysRole> {
    /**
     * 根据用户ID查询权限
     *
     * @param id 用户ID
     * @return 权限列表
     */
    Set<String> getRolePermsByUserId(Long id);
}

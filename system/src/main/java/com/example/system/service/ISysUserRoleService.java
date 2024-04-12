package com.example.system.service;

import com.example.system.domain.mtm.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户和角色关联表 服务类
 * </p>
 */
public interface ISysUserRoleService extends IService<SysUserRole> {
    /**
     * 根据角色ID查询用户列表
     * @param roleId 角色ID
     * @return 用户列表
     */
    List<SysUserRole> listByRoleId(Long roleId);
}

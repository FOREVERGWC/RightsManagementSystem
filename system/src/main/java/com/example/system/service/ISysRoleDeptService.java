package com.example.system.service;

import com.example.system.domain.mtm.SysRoleDept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色和部门关联表 服务类
 * </p>
 */
public interface ISysRoleDeptService extends IService<SysRoleDept> {
    /**
     * 根据角色ID列表移除部门
     * @param roleIds 角色ID列表
     */
    void removeByRoleIds(List<Long> roleIds);
}

package com.example.system.service;

import com.example.system.domain.mtm.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 服务类
 * </p>
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {
    /**
     * 根据角色ID列表移除菜单
     * @param roleIds 角色ID列表
     */
    void removeByRoleIds(List<Long> roleIds);
}

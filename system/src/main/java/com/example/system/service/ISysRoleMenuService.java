package com.example.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.system.domain.mtm.SysRoleMenu;

import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 服务类
 * </p>
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {
    /**
     * 根据角色ID列表移除关联
     *
     * @param roleIds 角色ID列表
     */
    void removeByRoleIds(List<Long> roleIds);

    /**
     * 根据菜单ID列表移除关联
     *
     * @param menuIds 菜单ID列表
     */
    void removeByMenuIds(List<Long> menuIds);
}

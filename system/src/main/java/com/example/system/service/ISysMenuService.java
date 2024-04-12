package com.example.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.domain.vo.RouterVo;
import com.example.system.domain.entity.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 */
public interface ISysMenuService extends IService<SysMenu> {
    /**
     * 根据角色ID列表批量查询菜单权限
     *
     * @param roleIds 角色ID列表
     * @return 菜单权限列表
     */
    Set<String> getPermsByRoleIds(List<Long> roleIds);

    /**
     * 根据用户ID查询菜单树
     *
     * @param userId 用户ID
     * @return 菜单树
     */
    List<SysMenu> getMenusTreeByUserId(Long userId);

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    List<RouterVo> buildMenus(List<SysMenu> menus);
}

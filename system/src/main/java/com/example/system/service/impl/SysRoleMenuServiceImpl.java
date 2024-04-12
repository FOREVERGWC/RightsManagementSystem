package com.example.system.service.impl;

import com.example.system.domain.mtm.SysRoleMenu;
import com.example.system.mapper.SysRoleMenuMapper;
import com.example.system.service.ISysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {
    @Override
    public void removeByRoleIds(List<Long> roleIds) {
        lambdaUpdate() //
                .in(SysRoleMenu::getRoleId, roleIds) //
                .remove();
    }
}

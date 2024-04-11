package com.example.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.system.domain.entity.SysMenu;
import com.example.system.domain.mtm.SysRoleMenu;
import com.example.system.mapper.SysMenuMapper;
import com.example.system.service.ISysMenuService;
import com.github.yulichang.toolkit.JoinWrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    @Override
    public Set<String> getPermsByRoleIds(List<Long> roleIds) {
        if (CollectionUtil.isEmpty(roleIds)) {
            return new HashSet<>();
        }
        MPJLambdaWrapper<SysMenu> wrapper = JoinWrappers.lambda(SysMenu.class) //
                .distinct() //
                .select(SysMenu::getPerms) //
                .innerJoin(SysRoleMenu.class, SysRoleMenu::getMenuId, SysMenu::getId) //
                .in(SysRoleMenu::getRoleId, roleIds);
        List<SysMenu> menus = list(wrapper);
        return menus.stream().map(SysMenu::getPerms).collect(Collectors.toSet());
    }
}

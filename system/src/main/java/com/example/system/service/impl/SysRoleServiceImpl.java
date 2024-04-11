package com.example.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.domain.entity.SysRole;
import com.example.common.domain.entity.SysUser;
import com.example.system.domain.mtm.SysUserRole;
import com.example.system.mapper.SysRoleMapper;
import com.example.system.service.ISysRoleService;
import com.github.yulichang.toolkit.JoinWrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    @Override
    public Set<String> getRolePermsByUserId(Long id) {
        MPJLambdaWrapper<SysRole> wrapper = JoinWrappers.lambda(SysRole.class) //
                .distinct() //
                .selectAll(SysRole.class) //
                .leftJoin(SysUserRole.class, SysUserRole::getRoleId, SysRole::getId) //
                .leftJoin(SysUser.class, SysUser::getId, SysUserRole::getUserId) //
                .eq(SysRole::getDeleted, 0) //
                .eq(SysUserRole::getUserId, id);
        List<SysRole> perms = list(wrapper);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms) {
            if (StrUtil.isNotBlank(perm.getCode())) {
                permsSet.addAll(Arrays.asList(perm.getCode().trim().split(",")));
            }
        }
        return permsSet;
    }
}

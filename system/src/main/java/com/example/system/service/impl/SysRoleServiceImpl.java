package com.example.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.constant.DeleteConstant;
import com.example.common.domain.dto.edit.StatusEditDto;
import com.example.common.domain.entity.SysRole;
import com.example.common.domain.entity.SysUser;
import com.example.common.domain.query.RoleQuery;
import com.example.common.exception.ServiceException;
import com.example.system.domain.mtm.SysUserRole;
import com.example.system.mapper.SysRoleMapper;
import com.example.system.service.ISysRoleDeptService;
import com.example.system.service.ISysRoleMenuService;
import com.example.system.service.ISysRoleService;
import com.example.system.service.ISysUserRoleService;
import com.github.yulichang.toolkit.JoinWrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    @Resource
    private ISysUserRoleService sysUserRoleService;
    @Resource
    private ISysRoleMenuService sysRoleMenuService;
    @Resource
    private ISysRoleDeptService sysRoleDeptService;

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

    @Override
    public List<SysRole> getList(SysRole sysRole) {
        return lambdaQuery() //
                .eq(sysRole.getId() != null, SysRole::getId, sysRole.getId()) //
                .eq(sysRole.getStatus() != null, SysRole::getStatus, sysRole.getStatus()) //
                .eq(SysRole::getDeleted, DeleteConstant.NORMAL) //
                .like(StrUtil.isNotBlank(sysRole.getName()), SysRole::getName, sysRole.getName()) //
                .eq(StrUtil.isNotBlank(sysRole.getCode()), SysRole::getCode, sysRole.getCode()) //
                .orderByAsc(SysRole::getSort) //
                .list();
    }

    @Override
    public Page<SysRole> getPage(RoleQuery query) {
        return lambdaQuery() //
                .eq(query.getId() != null, SysRole::getId, query.getId()) //
                .eq(query.getStatus() != null, SysRole::getStatus, query.getStatus()) //
                .eq(SysRole::getDeleted, DeleteConstant.NORMAL) //
                .like(StrUtil.isNotBlank(query.getName()), SysRole::getName, query.getName()) //
                .eq(StrUtil.isNotBlank(query.getCode()), SysRole::getCode, query.getCode()) //
                .orderByAsc(SysRole::getSort) //
                .page(new Page<>(query.getPageNo(), query.getPageSize()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void status(StatusEditDto dto) {
        List<SysRole> sysRoles = dto.getIds().stream().map(item -> {
            SysRole sysRole = new SysRole();
            sysRole.setId(item) //
                    .setStatus(dto.getStatus());
            return sysRole;
        }).toList();
        updateBatchById(sysRoles);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<Long> ids) {
        List<SysRole> sysRoles = listByIds(ids);
        Map<Long, List<SysUserRole>> map = sysUserRoleService.listMapByRoleIds(ids);
        sysRoles.stream() //
                .filter(sysRole -> map.get(sysRole.getId()) != null && !map.get(sysRole.getId()).isEmpty()) //
                .findFirst() //
                .ifPresent(sysRole -> {
                    throw new ServiceException(String.format("%1$s已分配，不能删除！", sysRole.getName()));
                });
        sysRoleMenuService.removeByRoleIds(ids);
        sysRoleDeptService.removeByRoleIds(ids);
        removeBatchByIds(ids);
    }
}

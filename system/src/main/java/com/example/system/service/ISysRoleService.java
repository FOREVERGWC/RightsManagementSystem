package com.example.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.domain.dto.edit.StatusEditDto;
import com.example.common.domain.entity.SysRole;
import com.example.common.domain.query.RoleQuery;

import java.util.List;
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

    /**
     * 查询角色列表
     * @param sysRole 角色信息实体
     * @return 角色列表
     */
    List<SysRole> getList(SysRole sysRole);

    /**
     * 查询角色分页
     * @param query 角色信息查询实体
     * @return 角色分页
     */
    Page<SysRole> getPage(RoleQuery query);

    /**
     * 修改角色状态
     * @param dto 状态编辑实体
     */
    void status(StatusEditDto dto);

    /**
     * 移除角色
     * @param ids 角色ID列表
     */
    void remove(List<Long> ids);
}

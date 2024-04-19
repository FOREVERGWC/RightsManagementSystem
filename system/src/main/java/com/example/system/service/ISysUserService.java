package com.example.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.domain.dto.edit.StatusEditDto;
import com.example.common.domain.entity.SysUser;
import com.example.common.domain.query.UserQuery;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 根据用户ID记录登录信息
     *
     * @param userId 用户ID
     */
    void recordLoginInfo(Long userId);

    /**
     * 根据用户名判断是否唯一
     *
     * @param username 用户名
     * @return 结果
     */
    boolean checkUsernameUnique(String username);

    /**
     * 根据用户名获取用户对象
     *
     * @param username 用户名
     * @return 用户对象
     */
    SysUser getByUsername(String username);

    /**
     * 查询用户列表
     *
     * @param sysUser 用户信息实体
     * @return 用户列表
     */
    List<SysUser> getList(SysUser sysUser);

    /**
     * 查询用户分页
     *
     * @param query 用户信息查询实体
     * @return 用户分页
     */
    Page<SysUser> getPage(UserQuery query);

    /**
     * 修改用户状态
     *
     * @param dto 状态编辑实体
     */
    void status(StatusEditDto dto);

    /**
     * 删除用户
     *
     * @param ids 用户ID列表
     */
    void remove(List<Long> ids);
}

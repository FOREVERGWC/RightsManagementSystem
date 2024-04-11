package com.example.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.system.domain.entity.SysUser;

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
}

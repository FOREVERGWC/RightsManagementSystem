package com.example.system.service;

import com.example.system.domain.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

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
}

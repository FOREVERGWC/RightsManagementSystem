package com.example.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.system.domain.entity.SysLoginInfo;

/**
 * <p>
 * 系统访问记录表 服务类
 * </p>
 */
public interface ISysLoginInfoService extends IService<SysLoginInfo> {
    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param msg      消息
     */
    void recordSysLoginInfo(String username, Integer status, String msg);
}

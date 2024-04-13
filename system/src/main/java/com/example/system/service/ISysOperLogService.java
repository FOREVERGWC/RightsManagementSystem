package com.example.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.system.domain.entity.SysOperLog;

/**
 * <p>
 * 操作日志记录表 服务类
 * </p>
 */
public interface ISysOperLogService extends IService<SysOperLog> {
    /**
     * 记录操作日志
     *
     * @param sysOperLog 操作日志
     */
    void recordOper(SysOperLog sysOperLog);
}

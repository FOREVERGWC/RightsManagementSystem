package com.example.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.utils.AddressUtils;
import com.example.system.domain.entity.SysOperLog;
import com.example.system.mapper.SysOperLogMapper;
import com.example.system.service.ISysOperLogService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * <p>
 * 操作日志记录表 服务实现类
 * </p>
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements ISysOperLogService {
    @Resource
    private Executor threadPoolTaskExecutor;

    @Override
    public void recordOper(SysOperLog sysOperLog) {
        String location = AddressUtils.getRealAddressByIP(sysOperLog.getOperIp());
        CompletableFuture.runAsync(() -> {
            sysOperLog.setOperLocation(location);
            sysOperLog.setOperTime(new Date());
            save(sysOperLog);
        }, threadPoolTaskExecutor);
    }
}

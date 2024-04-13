package com.example.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.utils.AddressUtils;
import com.example.system.domain.entity.SysOperLog;
import com.example.system.mapper.SysOperLogMapper;
import com.example.system.service.ISysOperLogService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 操作日志记录表 服务实现类
 * </p>
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements ISysOperLogService {
    @Override
    public void recordOper(SysOperLog sysOperLog) {
        sysOperLog.setOperLocation(AddressUtils.getRealAddressByIP(sysOperLog.getOperIp()));
        sysOperLog.setOperTime(new Date());
        save(sysOperLog);
    }
}

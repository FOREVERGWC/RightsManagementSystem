package com.example.system.service.impl;

import cn.hutool.http.useragent.UserAgent;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.utils.AddressUtils;
import com.example.common.utils.ServletUtils;
import com.example.system.domain.entity.SysLoginInfo;
import com.example.system.mapper.SysLoginInfoMapper;
import com.example.system.service.ISysLoginInfoService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 系统访问记录表 服务实现类
 * </p>
 */
@Service
public class SysLoginInfoServiceImpl extends ServiceImpl<SysLoginInfoMapper, SysLoginInfo> implements ISysLoginInfoService {
    @Override
    public void recordSysLoginInfo(String username, Integer status, String msg) {
        String loginIp = ServletUtils.getUserIp();
        String loginLocation = AddressUtils.getRealAddressByIP(loginIp);
        UserAgent ua = ServletUtils.getUserAgent();
        SysLoginInfo sysLoginInfo = SysLoginInfo.builder() //
                .username(username) //
                .loginIp(loginIp) //
                .loginTime(new Date()) //
                .loginLocation(loginLocation) //
                .browser(ua.getBrowser().getName()) //
                .os(ua.getOs().getName()) //
                .status(status) //
                .msg(msg) //
                .build();
        save(sysLoginInfo);
    }
}

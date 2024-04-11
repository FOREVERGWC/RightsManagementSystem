package com.example.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.utils.ServletUtils;
import com.example.system.domain.entity.SysUser;
import com.example.system.mapper.SysUserMapper;
import com.example.system.service.ISysUserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Override
    public void recordLoginInfo(Long userId) {
        String loginIp = ServletUtils.getUserIp();
        SysUser sysUser = SysUser.builder() //
                .id(userId) //
                .loginIp(loginIp) //
                .loginTime(new Date()) //
                .build();
        updateById(sysUser);
    }

    @Override
    public boolean checkUsernameUnique(String username) {
        List<SysUser> list = lambdaQuery() //
                .eq(SysUser::getUsername, username) //
                .list();
        return list.isEmpty();
    }

    @Override
    public SysUser getByUsername(String username) {
        return lambdaQuery() //
                .eq(SysUser::getUsername, username) //
                .one();
    }
}

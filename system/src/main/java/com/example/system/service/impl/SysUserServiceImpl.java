package com.example.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.constant.DeleteConstant;
import com.example.common.domain.dto.edit.StatusEditDto;
import com.example.common.domain.entity.SysUser;
import com.example.common.domain.query.UserQuery;
import com.example.common.utils.ServletUtils;
import com.example.system.mapper.SysUserMapper;
import com.example.system.service.ISysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<SysUser> getList(SysUser sysUser) {
        return lambdaQuery() //
                .eq(sysUser.getId() != null, SysUser::getId, sysUser.getId()) //
                .eq(sysUser.getStatus() != null, SysUser::getStatus, sysUser.getStatus()) //
                .eq(SysUser::getDeleted, DeleteConstant.NORMAL) //
                .eq(StrUtil.isNotBlank(sysUser.getUsername()), SysUser::getUsername, sysUser.getUsername()) //
                .eq(sysUser.getDeptId() != null, SysUser::getDeptId, sysUser.getDeptId()) //
                .eq(StrUtil.isNotBlank(sysUser.getPhone()), SysUser::getPhone, sysUser.getPhone()) //
                .list();
    }

    @Override
    public Page<SysUser> getPage(UserQuery query) {
        return lambdaQuery() //
                .eq(query.getId() != null, SysUser::getId, query.getId()) //
                .eq(query.getStatus() != null, SysUser::getStatus, query.getStatus()) //
                .eq(SysUser::getDeleted, DeleteConstant.NORMAL) //
                .eq(StrUtil.isNotBlank(query.getUsername()), SysUser::getUsername, query.getUsername()) //
                .eq(query.getDeptId() != null, SysUser::getDeptId, query.getDeptId()) //
                .eq(StrUtil.isNotBlank(query.getPhone()), SysUser::getPhone, query.getPhone()) //
                .page(new Page<>(query.getPageNo(), query.getPageSize()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void status(StatusEditDto dto) {
        lambdaUpdate() //
                .set(SysUser::getStatus, dto.getStatus()) //
                .in(SysUser::getId, dto.getIds()) //
                .update();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<Long> ids) {
        removeBatchByIds(ids);
    }
}

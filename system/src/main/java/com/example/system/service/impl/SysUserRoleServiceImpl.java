package com.example.system.service.impl;

import com.example.system.domain.mtm.SysUserRole;
import com.example.system.mapper.SysUserRoleMapper;
import com.example.system.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
    @Override
    public List<SysUserRole> listByRoleId(Long roleId) {
        return lambdaQuery() //
                .eq(SysUserRole::getRoleId, roleId) //
                .list();
    }
}

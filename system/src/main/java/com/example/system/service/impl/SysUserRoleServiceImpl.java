package com.example.system.service.impl;

import com.example.system.domain.mtm.SysUserRole;
import com.example.system.mapper.SysUserRoleMapper;
import com.example.system.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public Map<Long, List<SysUserRole>> listMapByRoleIds(List<Long> roleIds) {
        List<SysUserRole> list = lambdaQuery() //
                .in(SysUserRole::getRoleId, roleIds) //
                .list();
        return list.stream().collect(Collectors.groupingBy(SysUserRole::getRoleId));
    }
}

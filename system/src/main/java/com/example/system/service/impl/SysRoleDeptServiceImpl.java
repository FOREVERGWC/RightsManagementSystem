package com.example.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.system.domain.mtm.SysRoleDept;
import com.example.system.mapper.SysRoleDeptMapper;
import com.example.system.service.ISysRoleDeptService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色和部门关联表 服务实现类
 * </p>
 */
@Service
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptMapper, SysRoleDept> implements ISysRoleDeptService {
    @Override
    public void removeByRoleIds(List<Long> roleIds) {
        lambdaUpdate() //
                .in(SysRoleDept::getRoleId, roleIds) //
                .remove();
    }
}

package com.example.system.mapper;

import com.example.system.domain.mtm.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户和角色关联表 Mapper 接口
 * </p>
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

}

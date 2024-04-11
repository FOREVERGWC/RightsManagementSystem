package com.example.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.domain.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    @Select("SELECT r.id, r.name, r.code, r.sort, r.status, r.create_by, r.create_time, r.update_by, r.update_time, r.remark, r.deleted FROM sys_role AS r INNER JOIN sys_user_role AS ur ON r.id = ur.role_id WHERE r.deleted = 0 AND ur.user_id = #{id}")
    List<SysRole> selectBySysUserId(Serializable id);
}

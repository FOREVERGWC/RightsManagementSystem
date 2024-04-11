package com.example.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.system.domain.entity.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    @Override
    @Select("SELECT id, username, password, avatar, email, nickname, gender, phone, login_ip, login_time, status, create_by, create_time, update_by, update_time, remark, deleted FROM sys_user ${ew.customSqlSegment}")
    @Results({ //
            @Result(column = "id", property = "id"), //
            @Result(column = "id", property = "roles", many = @Many(select = "com.example.system.mapper.SysRoleMapper.selectBySysUserId")) //
    })
    <P extends IPage<SysUser>> P selectPage(P page, @Param(Constants.WRAPPER) Wrapper<SysUser> queryWrapper);

    @Override
    @Select("SELECT id, username, password, avatar, email, nickname, gender, phone, login_ip, login_time, status, create_by, create_time, update_by, update_time, remark, deleted FROM sys_user ${ew.customSqlSegment}")
    @Results({ //
            @Result(column = "id", property = "id"), //
            @Result(column = "id", property = "roles", many = @Many(select = "com.example.system.mapper.SysRoleMapper.selectBySysUserId")) //
    })
    List<SysUser> selectList(@Param(Constants.WRAPPER) Wrapper<SysUser> queryWrapper);
}

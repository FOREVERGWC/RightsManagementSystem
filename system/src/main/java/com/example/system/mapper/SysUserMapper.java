package com.example.system.mapper;

import com.example.system.domain.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}

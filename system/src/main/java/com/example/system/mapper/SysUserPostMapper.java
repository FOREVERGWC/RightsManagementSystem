package com.example.system.mapper;

import com.example.system.domain.mtm.SysUserPost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户与岗位关联表 Mapper 接口
 * </p>
 */
@Mapper
public interface SysUserPostMapper extends BaseMapper<SysUserPost> {

}

package com.example.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.system.domain.entity.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 操作日志记录表 Mapper 接口
 * </p>
 */
@Mapper
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {

}

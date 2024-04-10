package com.example.system.service.impl;

import com.example.system.domain.mtm.SysUserPost;
import com.example.system.mapper.SysUserPostMapper;
import com.example.system.service.ISysUserPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与岗位关联表 服务实现类
 * </p>
 */
@Service
public class SysUserPostServiceImpl extends ServiceImpl<SysUserPostMapper, SysUserPost> implements ISysUserPostService {

}

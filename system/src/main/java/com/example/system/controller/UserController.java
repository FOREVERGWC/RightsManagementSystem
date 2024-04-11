package com.example.system.controller;

import com.example.common.domain.entity.SysUser;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.system.service.ISysUserService;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/system/user")
@Tag(name = "用户信息表接口", description = "用户信息表接口")
public class UserController {
    @Resource
    private ISysUserService sysUserService;

    @PostMapping("/test")
    public void test(@RequestBody SysUser sysUser) {
        System.out.println(sysUser);
    }
}

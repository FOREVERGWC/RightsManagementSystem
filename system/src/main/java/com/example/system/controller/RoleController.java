package com.example.system.controller;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.system.service.ISysRoleService;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/system/role")
@Tag(name = "角色信息表接口", description = "角色信息表接口")
public class RoleController {
    @Resource
    private ISysRoleService sysRoleService;
}

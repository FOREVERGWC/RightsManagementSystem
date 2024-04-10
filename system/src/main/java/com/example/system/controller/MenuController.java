package com.example.system.controller;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.system.service.ISysMenuService;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/system/menu")
@Tag(name = "菜单权限表接口", description = "菜单权限表接口")
public class MenuController {
    @Resource
    private ISysMenuService sysMenuService;
}

package com.example.system.controller;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.system.service.ISysLoginInfoService;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统访问记录表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/system/loginInfo")
@Tag(name = "系统访问记录表接口", description = "系统访问记录表接口")
public class LoginInfoController {
    @Resource
    private ISysLoginInfoService sysLoginInfoService;
}

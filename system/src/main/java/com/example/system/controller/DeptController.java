package com.example.system.controller;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.system.service.ISysDeptService;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/system/dept")
@Tag(name = "部门表接口", description = "部门表接口")
public class DeptController {
    @Resource
    private ISysDeptService sysDeptService;
}

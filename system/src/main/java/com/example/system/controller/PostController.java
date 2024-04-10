package com.example.system.controller;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.system.service.ISysPostService;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 岗位信息表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/system/post")
@Tag(name = "岗位信息表接口", description = "岗位信息表接口")
public class PostController {
    @Resource
    private ISysPostService sysPostService;
}

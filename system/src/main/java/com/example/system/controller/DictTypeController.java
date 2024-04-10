package com.example.system.controller;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.system.service.ISysDictTypeService;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 字典类型表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/system/dictType")
@Tag(name = "字典类型表接口", description = "字典类型表接口")
public class DictTypeController {
    @Resource
    private ISysDictTypeService sysDictTypeService;
}

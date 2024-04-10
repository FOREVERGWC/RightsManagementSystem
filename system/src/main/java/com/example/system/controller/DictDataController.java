package com.example.system.controller;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.system.service.ISysDictDataService;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 字典数据表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/system/dictData")
@Tag(name = "字典数据表接口", description = "字典数据表接口")
public class DictDataController {
    @Resource
    private ISysDictDataService sysDictDataService;
}

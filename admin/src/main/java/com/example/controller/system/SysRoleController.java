package com.example.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.constant.MsgConstant;
import com.example.common.domain.R;
import com.example.common.domain.dto.add.SysRoleAddDto;
import com.example.common.domain.dto.edit.StatusEditDto;
import com.example.common.domain.dto.edit.SysRoleEditDto;
import com.example.common.domain.entity.SysRole;
import com.example.common.domain.query.RoleQuery;
import com.example.system.service.ISysRoleService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sys/role")
public class SysRoleController {
    @Resource
    private ISysRoleService sysRoleService;

    // TODO: 2024/4/12 操作日志
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('system:role:list')")
    public R list(SysRole sysRole) {
        List<SysRole> list = sysRoleService.getList(sysRole);
        return R.success(MsgConstant.QUERY_SUCCESS).put("list", list);
    }

    @GetMapping("/page")
    @PreAuthorize("hasAuthority('system:role:list')")
    public R page(RoleQuery query) {
        Page<SysRole> page = sysRoleService.getPage(query);
        return R.success(MsgConstant.QUERY_SUCCESS).put("page", page);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('system:role:query')")
    public R getById(@PathVariable(value = "id") Long id) {
        SysRole sysRole = sysRoleService.getById(id);
        return R.success(MsgConstant.QUERY_SUCCESS).put("sysRole", sysRole);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('system:role:add')")
    public R add(@Valid @RequestBody SysRoleAddDto dto) {
        SysRole sysRole = SysRole.builder() //
                .name(dto.getName()) //
                .code(dto.getCode()) //
                .sort(dto.getSort()) //
                .remark(dto.getRemark()) //
                .build();
        sysRoleService.save(sysRole);
        return R.success(MsgConstant.ADD_SUCCESS);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('system:role:edit')")
    public R edit(@Valid @RequestBody SysRoleEditDto dto) {
        SysRole sysRole = SysRole.builder() //
                .id(dto.getId()) //
                .name(dto.getName()) //
                .code(dto.getCode()) //
                .sort(dto.getSort()) //
                .status(dto.getStatus()) //
                .remark(dto.getRemark()) //
                .build();
        sysRoleService.updateById(sysRole);
        return R.success(MsgConstant.EDIT_SUCCESS);
    }

    @PreAuthorize("hasAuthority('system:role:edit')")
    @PutMapping("/status")
    public R status(@RequestBody StatusEditDto dto) {
        sysRoleService.status(dto);
        return R.success(MsgConstant.EDIT_SUCCESS);
    }

    @PreAuthorize("hasAuthority('system:role:remove')")
    @DeleteMapping
    public R remove(@RequestBody List<Long> ids) {
        sysRoleService.remove(ids);
        return R.success(MsgConstant.DELETE_SUCCESS);
    }
}

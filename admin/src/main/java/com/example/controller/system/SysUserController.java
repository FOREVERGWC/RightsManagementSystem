package com.example.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.annotation.Log;
import com.example.common.constant.MsgConstant;
import com.example.common.domain.R;
import com.example.common.domain.dto.add.SysRoleAddDto;
import com.example.common.domain.dto.edit.StatusEditDto;
import com.example.common.domain.dto.edit.SysRoleEditDto;
import com.example.common.domain.entity.SysUser;
import com.example.common.domain.query.UserQuery;
import com.example.common.enums.BusinessType;
import com.example.system.service.ISysUserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sys/user")
public class SysUserController {
    @Resource
    private ISysUserService sysUserService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('system:user:list')")
    public R list(SysUser sysUser) {
        List<SysUser> list = sysUserService.getList(sysUser);
        return R.success(MsgConstant.QUERY_SUCCESS).put("list", list);
    }

    @GetMapping("/page")
    @PreAuthorize("hasAuthority('system:user:list')")
    public R page(UserQuery query) {
        Page<SysUser> page = sysUserService.getPage(query);
        return R.success(MsgConstant.QUERY_SUCCESS).put("page", page);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('system:user:query')")
    public R getById(@PathVariable(value = "id") Long id) {
        SysUser sysUser = sysUserService.getById(id);
        return R.success(MsgConstant.QUERY_SUCCESS).put("sysUser", sysUser);
    }

    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    @PreAuthorize("hasAuthority('system:user:add')")
    public R add(@Valid @RequestBody SysRoleAddDto dto) {
        SysUser sysRole = SysUser.builder() //
                .remark(dto.getRemark()) //
                .build();
        sysUserService.save(sysRole);
        return R.success(MsgConstant.ADD_SUCCESS);
    }

    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    @PreAuthorize("hasAuthority('system:user:edit')")
    public R edit(@Valid @RequestBody SysRoleEditDto dto) {
        SysUser sysRole = SysUser.builder() //
                .remark(dto.getRemark()) //
                .build();
        sysUserService.updateById(sysRole);
        return R.success(MsgConstant.EDIT_SUCCESS);
    }

    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/status")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public R status(@RequestBody StatusEditDto dto) {
        sysUserService.status(dto);
        return R.success(MsgConstant.EDIT_SUCCESS);
    }

    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping
    @PreAuthorize("hasAuthority('system:user:remove')")
    public R remove(@RequestBody List<Long> ids) {
        sysUserService.remove(ids);
        return R.success(MsgConstant.DELETE_SUCCESS);
    }
}

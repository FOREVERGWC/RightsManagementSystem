package com.example.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.annotation.Log;
import com.example.common.constant.MsgConstant;
import com.example.common.domain.R;
import com.example.common.domain.dto.add.SysMenuAddDto;
import com.example.common.domain.dto.edit.StatusEditDto;
import com.example.common.domain.dto.edit.SysMenuEditDto;
import com.example.common.domain.query.MenuQuery;
import com.example.common.enums.BusinessType;
import com.example.system.domain.entity.SysMenu;
import com.example.system.service.ISysMenuService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sys/menu")
public class SysMenuController {
    @Resource
    private ISysMenuService sysMenuService;

    @GetMapping("/tree")
    @PreAuthorize("hasAuthority('system:menu:list')")
    public R tree(SysMenu sysMenu) {
        List<SysMenu> tree = sysMenuService.getTree(sysMenu);
        return R.success(MsgConstant.QUERY_SUCCESS) //
                .put("tree", tree);
    }

    @GetMapping("/page")
    @PreAuthorize("hasAuthority('system:menu:list')")
    public R page(MenuQuery query) {
        Page<SysMenu> page = sysMenuService.getPage(query);
        return R.success(MsgConstant.QUERY_SUCCESS).put("page", page);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('system:menu:query')")
    public R getById(@PathVariable(value = "id") Long id) {
        SysMenu sysMenu = sysMenuService.getById(id);
        return R.success(MsgConstant.QUERY_SUCCESS).put("sysMenu", sysMenu);
    }

    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping
    @PreAuthorize("hasAuthority('system:menu:add')")
    public R add(@Valid @RequestBody SysMenuAddDto dto) {
        SysMenu sysMenu = SysMenu.builder() //
                .name(dto.getName()) //
                .icon(dto.getIcon()) //
                .parentId(dto.getParentId()) //
                .path(dto.getPath()) //
                .component(dto.getComponent()) //
                .isFrame(dto.getIsFrame()) //
                .isCache(dto.getIsCache()) //
                .menuType(dto.getMenuType()) //
                .perms(dto.getPerms()) //
                .isVisible(dto.getIsVisible()) //
                .sort(dto.getSort()) //
                .remark(dto.getRemark()) //
                .build();
        sysMenuService.save(sysMenu);
        return R.success(MsgConstant.ADD_SUCCESS);
    }

    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    @PreAuthorize("hasAuthority('system:menu:edit')")
    public R edit(@Valid @RequestBody SysMenuEditDto dto) {
        SysMenu sysMenu = SysMenu.builder() //
                .id(dto.getId()) //
                .name(dto.getName()) //
                .icon(dto.getIcon()) //
                .parentId(dto.getParentId()) //
                .path(dto.getPath()) //
                .component(dto.getComponent()) //
                .isFrame(dto.getIsFrame()) //
                .isCache(dto.getIsCache()) //
                .menuType(dto.getMenuType()) //
                .perms(dto.getPerms()) //
                .isVisible(dto.getIsVisible()) //
                .sort(dto.getSort()) //
                .status(dto.getStatus()) //
                .remark(dto.getRemark()) //
                .build();
        sysMenuService.updateById(sysMenu);
        return R.success(MsgConstant.EDIT_SUCCESS);
    }

    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PreAuthorize("hasAuthority('system:menu:edit')")
    @PutMapping("/status")
    public R status(@RequestBody StatusEditDto dto) {
        sysMenuService.status(dto);
        return R.success(MsgConstant.EDIT_SUCCESS);
    }

    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @PreAuthorize("hasAuthority('system:menu:remove')")
    @DeleteMapping
    public R remove(@RequestBody List<Long> ids) {
        sysMenuService.remove(ids);
        return R.success(MsgConstant.DELETE_SUCCESS);
    }
}

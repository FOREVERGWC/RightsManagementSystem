package com.example.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.constant.*;
import com.example.common.domain.dto.edit.StatusEditDto;
import com.example.common.domain.entity.SysRole;
import com.example.common.domain.entity.SysUser;
import com.example.common.domain.query.MenuQuery;
import com.example.common.domain.vo.MetaVo;
import com.example.common.domain.vo.RouterVo;
import com.example.common.utils.DataUtils;
import com.example.system.domain.entity.SysMenu;
import com.example.system.domain.mtm.SysRoleMenu;
import com.example.system.domain.mtm.SysUserRole;
import com.example.system.mapper.SysMenuMapper;
import com.example.system.service.ISysMenuService;
import com.example.system.service.ISysRoleMenuService;
import com.github.yulichang.toolkit.JoinWrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    @Resource
    private ISysRoleMenuService sysRoleMenuService;

    @Override
    public Set<String> getPermsByRoleIds(List<Long> roleIds) {
        if (CollectionUtil.isEmpty(roleIds)) {
            return new HashSet<>();
        }
        MPJLambdaWrapper<SysMenu> wrapper = JoinWrappers.lambda(SysMenu.class) //
                .distinct() //
                .select(SysMenu::getPerms) //
                .innerJoin(SysRoleMenu.class, SysRoleMenu::getMenuId, SysMenu::getId) //
                .in(SysRoleMenu::getRoleId, roleIds);
        List<SysMenu> menus = list(wrapper);
        return menus.stream().map(SysMenu::getPerms).collect(Collectors.toSet());
    }

    @Override
    public List<SysMenu> getMenusTreeByUserId(Long userId) {
        MPJLambdaWrapper<SysMenu> wrapper = JoinWrappers.lambda(SysMenu.class) //
                .distinct() //
                .selectAll(SysMenu.class) //
                .leftJoin(SysRoleMenu.class, SysRoleMenu::getMenuId, SysMenu::getId) //
                .leftJoin(SysUserRole.class, SysUserRole::getRoleId, SysRoleMenu::getRoleId) //
                .leftJoin(SysRole.class, SysRole::getId, SysUserRole::getRoleId) //
                .leftJoin(SysUser.class, SysUser::getId, SysUserRole::getUserId) //
                .eq(SysUser::getId, userId) //
                .in(SysMenu::getMenuType, MenuConstant.DIRECTORY, MenuConstant.MENUS) //
                .eq(SysMenu::getStatus, StatusConstant.NORMAL) //
                .eq(SysRole::getStatus, StatusConstant.NORMAL) //
                .orderByAsc(SysMenu::getParentId) //
                .orderByAsc(SysMenu::getSort);
        List<SysMenu> list = list(wrapper);
        return DataUtils.listToTree(list, SysMenu::getParentId, SysMenu::setChildren, SysMenu::getId, 0L);
    }

    @Override
    public List<RouterVo> buildMenus(List<SysMenu> menus) {
        List<RouterVo> routers = new LinkedList<>();
        for (SysMenu menu : menus) {
            RouterVo router = new RouterVo();
            router.setHidden(VisibleConstant.INVISIBLE.equals(menu.getIsVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaVo(menu.getName(), menu.getIcon(), menu.getIsCache(), menu.getPath()));
            List<SysMenu> cMenus = menu.getChildren();
            if (CollectionUtil.isNotEmpty(cMenus) && Objects.equals(menu.getMenuType(), MenuConstant.DIRECTORY)) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMenuFrame(menu)) {
                router.setMeta(null);
                List<RouterVo> childrenList = new ArrayList<>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StrUtil.upperFirst(menu.getPath()));
                children.setMeta(new MetaVo(menu.getName(), menu.getIcon(), menu.getIsCache(), menu.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            } else if (menu.getParentId() == 0L && isInnerLink(menu)) {
                router.setMeta(new MetaVo(menu.getName(), menu.getIcon()));
                router.setPath("/");
                List<RouterVo> childrenList = new ArrayList<>();
                RouterVo children = new RouterVo();
                String routerPath = innerLinkReplaceEach(menu.getPath());
                children.setPath(routerPath);
                children.setComponent(Constant.INNER_LINK);
                children.setName(StrUtil.upperFirst(routerPath));
                children.setMeta(new MetaVo(menu.getName(), menu.getIcon(), menu.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    @Override
    public List<SysMenu> getTree(SysMenu sysMenu) {
        List<SysMenu> list = lambdaQuery() //
                .eq(sysMenu.getId() != null, SysMenu::getId, sysMenu.getId()) //
                .eq(sysMenu.getStatus() != null, SysMenu::getStatus, sysMenu.getStatus()) //
                .eq(SysMenu::getDeleted, DeleteConstant.NORMAL) //
                .like(StrUtil.isNotBlank(sysMenu.getName()), SysMenu::getName, sysMenu.getName()) //
                .eq(sysMenu.getParentId() != null, SysMenu::getParentId, sysMenu.getParentId()) //
                .orderByAsc(SysMenu::getSort) //
                .list();
        return DataUtils.listToTree(list, SysMenu::getParentId, SysMenu::setChildren, SysMenu::getId, 0L);
    }

    @Override
    public Page<SysMenu> getPage(MenuQuery query) {
        return lambdaQuery() //
                .eq(query.getId() != null, SysMenu::getId, query.getId()) //
                .eq(query.getStatus() != null, SysMenu::getStatus, query.getStatus()) //
                .eq(SysMenu::getDeleted, DeleteConstant.NORMAL) //
                .like(StrUtil.isNotBlank(query.getName()), SysMenu::getName, query.getName()) //
                .eq(query.getParentId() != null, SysMenu::getParentId, query.getParentId()) //
                .orderByAsc(SysMenu::getSort) //
                .page(new Page<>(query.getPageNo(), query.getPageSize()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void status(StatusEditDto dto) {
        List<SysMenu> sysMenus = dto.getIds().stream().map(item -> {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setId(item) //
                    .setStatus(dto.getStatus());
            return sysMenu;
        }).toList();
        updateBatchById(sysMenus);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<Long> ids) {
        sysRoleMenuService.removeByMenuIds(ids);
        removeBatchByIds(ids);
    }

    /**
     * 是否为内链组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    private boolean isInnerLink(SysMenu menu) {
        return Constant.NO.equals(menu.getIsFrame()) && Validator.isUrl(menu.getPath());
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    private String getRouteName(SysMenu menu) {
        String name = StrUtil.upperFirst(menu.getPath());
        if (menu.getParentId() == 0L && Objects.equals(MenuConstant.MENUS, menu.getMenuType()) && Constant.YES.equals(menu.getIsFrame())) {
            name = "";
        }
        return name;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    private String getRouterPath(SysMenu menu) {
        String routerPath = menu.getPath();
        // 内链打开外网方式
        if (menu.getParentId() != 0L && isInnerLink(menu)) {
            routerPath = innerLinkReplaceEach(routerPath);
        }
        // 非外链并且是一级目录（类型为目录）
        if (0L == menu.getParentId() && Objects.equals(MenuConstant.DIRECTORY, menu.getMenuType()) && Constant.NO.equals(menu.getIsFrame())) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 内链域名特殊字符替换
     *
     * @return 替换后的内链域名
     */
    private String innerLinkReplaceEach(String path) {
        path = StrUtil.replace(path, Constant.HTTP, "");
        path = StrUtil.replace(path, Constant.HTTPS, "");
        path = StrUtil.replace(path, Constant.WWW, "");
        path = StrUtil.replace(path, ".", "/");
        path = StrUtil.replace(path, ":", "/");
        return path;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    private boolean isMenuFrame(SysMenu menu) {
        return menu.getParentId() == 0L && Objects.equals(MenuConstant.MENUS, menu.getMenuType()) && Constant.NO.equals(menu.getIsFrame());
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    private String getComponent(SysMenu menu) {
        String component = Constant.LAYOUT;
        if (StrUtil.isNotBlank(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        } else if (StrUtil.isBlank(menu.getComponent()) && menu.getParentId() != 0L && isInnerLink(menu)) {
            component = Constant.INNER_LINK;
        } else if (StrUtil.isBlank(menu.getComponent()) && isParentView(menu)) {
            component = Constant.PARENT_VIEW;
        }
        return component;
    }

    /**
     * 是否为父视图组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    private boolean isParentView(SysMenu menu) {
        return menu.getParentId() != 0L && Objects.equals(menu.getMenuType(), MenuConstant.DIRECTORY);
    }
}

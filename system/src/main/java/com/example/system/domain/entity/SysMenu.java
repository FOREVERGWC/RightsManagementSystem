package com.example.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.system.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * <p>
 * 菜单权限表
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
@Schema(name = "Menu", description = "菜单权限表")
public class SysMenu extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 8744102590234558528L;
    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    private String name;
    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    private String icon;
    /**
     * 父菜单ID
     */
    @Schema(description = "父菜单ID")
    private Long parentId;
    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer sort;
    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    private String path;
    /**
     * 组件路径
     */
    @Schema(description = "组件路径")
    private String component;
    /**
     * 是否框架（0否、1是）
     */
    @Schema(description = "是否框架（0否、1是）")
    private Integer isFrame;
    /**
     * 是否缓存（0否、1是）
     */
    @Schema(description = "是否缓存（0否、1是）")
    private Integer isCache;
    /**
     * 菜单类型（0目录、1菜单、2按钮）
     */
    @Schema(description = "菜单类型（0目录、1菜单、2按钮）")
    private Integer menuType;
    /**
     * 权限标识
     */
    @Schema(description = "权限标识")
    private String perms;
    /**
     * 是否可见（0否、1是）
     */
    @Schema(description = "是否可见（0否、1是）")
    private Integer isVisible;
}

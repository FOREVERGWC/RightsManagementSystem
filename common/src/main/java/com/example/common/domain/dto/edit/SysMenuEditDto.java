package com.example.common.domain.dto.edit;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 菜单编辑实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@Schema(name = "菜单编辑实体", description = "菜单编辑实体")
public class SysMenuEditDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -2659224070845997737L;
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @NotNull(message = "{entity.id.notNull}")
    private Long id;
    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    @NotBlank(message = "{menu.name.notBlank}")
    private String name;
    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    @NotBlank(message = "{menu.icon.notBlank}")
    private String icon;
    /**
     * 父菜单ID
     */
    @Schema(description = "父菜单ID")
    @NotNull(message = "{menu.parentId.notNull}")
    private Long parentId;
    /**
     * 路由地址
     */
    @Schema(description = "路由地址")
    @NotNull(message = "{menu.path.notNull}")
    private String path;
    /**
     * 组件路径
     */
    @Schema(description = "组件路径")
    @NotNull(message = "{menu.component.notNull}")
    private String component;
    /**
     * 是否框架（0否、1是）
     */
    @Schema(description = "是否框架（0否、1是）")
    @NotNull(message = "{menu.isFrame.notNull}")
    private Integer isFrame;
    /**
     * 是否缓存（0否、1是）
     */
    @Schema(description = "是否缓存（0否、1是）")
    @NotNull(message = "{menu.isCache.notNull}")
    private Integer isCache;
    /**
     * 菜单类型（0目录、1菜单、2按钮）
     */
    @Schema(description = "菜单类型（0目录、1菜单、2按钮）")
    @NotNull(message = "{menu.menuType.notNull}")
    private Integer menuType;
    /**
     * 权限标识
     */
    @Schema(description = "权限标识")
    @NotNull(message = "{menu.perms.notNull}")
    private String perms;
    /**
     * 是否可见（0否、1是）
     */
    @Schema(description = "是否可见（0否、1是）")
    @NotNull(message = "{menu.isVisible.notNull}")
    private Integer isVisible;
    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    @NotNull(message = "{menu.sort.notNull}")
    private Integer sort;
    /**
     * 状态
     */
    @Schema(description = "状态")
    @NotNull(message = "{entity.status.notNull}")
    private Integer status;
    /**
     * 备注
     */
    @Schema(description = "备注")
    @NotNull(message = "{entity.remark.notNull}")
    private String remark;
}

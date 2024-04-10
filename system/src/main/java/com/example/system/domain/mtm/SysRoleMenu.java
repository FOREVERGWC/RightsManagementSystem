package com.example.system.domain.mtm;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色和菜单关联表
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@TableName("sys_role_menu")
@Schema(name = "RoleMenu", description = "角色和菜单关联表")
public class SysRoleMenu implements Serializable {
    @Serial
    private static final long serialVersionUID = 7568433640229052160L;
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private Long roleId;
    /**
     * 菜单ID
     */
    @Schema(description = "菜单ID")
    private Long menuId;
}

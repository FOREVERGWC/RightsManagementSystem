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
 * 角色和部门关联表
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@TableName("sys_role_dept")
@Schema(name = "RoleDept", description = "角色和部门关联表")
public class SysRoleDept implements Serializable {
    @Serial
    private static final long serialVersionUID = -4818781188741428172L;
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
     * 部门ID
     */
    @Schema(description = "部门ID")
    private Long deptId;
}

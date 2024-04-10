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
 * 角色信息表
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
@Schema(name = "Role", description = "角色信息表")
public class SysRole extends BaseEntity {
    @Serial
    private static final long serialVersionUID = -3772905965939437615L;
    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String name;
    /**
     * 角色权限字符串
     */
    @Schema(description = "角色权限字符串")
    private String code;
    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer sort;
}

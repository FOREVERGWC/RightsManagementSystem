package com.example.common.domain.dto.edit;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色编辑实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@Schema(name = "角色编辑实体", description = "角色编辑实体")
public class SysRoleEditDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 9128455223896215406L;
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @NotNull(message = "{entity.id.notNull}")
    private Long id;
    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    @NotBlank(message = "{role.name.isNotBlank}")
    private String name;
    /**
     * 角色权限字符串
     */
    @Schema(description = "角色权限字符串")
    @NotBlank(message = "{role.code.isNotBlank}")
    private String code;
    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    @NotNull(message = "{role.sort.notNull}")
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

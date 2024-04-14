package com.example.common.domain.dto.add;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色添加实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@Schema(name = "角色添加实体", description = "角色添加实体")
public class SysRoleAddDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 4307682695754956228L;
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
     * 备注
     */
    @Schema(description = "备注")
    @NotNull(message = "{entity.remark.notNull}")
    private String remark;
}

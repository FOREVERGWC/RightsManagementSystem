package com.example.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * <p>
 * 部门表
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dept")
@Schema(name = "Dept", description = "部门表")
public class SysDept extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1209240574500095702L;
    /**
     * 父级ID
     */
    @Schema(description = "父级ID")
    private Long parentId;
    /**
     * 祖级列表
     */
    @Schema(description = "祖级列表")
    private String ancestors;
    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String name;
    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer sort;
    /**
     * 负责人
     */
    @Schema(description = "负责人")
    private Long leader;
}

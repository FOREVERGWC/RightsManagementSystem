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
 * 岗位信息表
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_post")
@Schema(name = "Post", description = "岗位信息表")
public class SysPost extends BaseEntity {
    @Serial
    private static final long serialVersionUID = -9048090516163941414L;
    /**
     * 岗位名称
     */
    @Schema(description = "岗位名称")
    private String name;
    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer sort;
}

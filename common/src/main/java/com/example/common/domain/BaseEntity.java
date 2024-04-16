package com.example.common.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
@Schema(name = "实体基类", description = "实体基类")
public class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 491855507808217243L;
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 状态(0正常、1停用)
     */
    @Schema(description = "状态(0正常、1停用)")
    @NotNull(message = "{entity.status.notNull}")
    private Integer status;
    /**
     * 创建者
     */
    @Schema(description = "创建者")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新者
     */
    @Schema(description = "更新者")
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;
    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
    /**
     * 备注
     */
    @Schema(description = "备注")
    @NotNull(message = "{entity.remark.notNull}")
    private String remark;
    /**
     * 逻辑删除（0正常、1删除）
     */
    @Schema(description = "逻辑删除（0正常、1删除）")
    @TableLogic
    private Integer deleted;
}

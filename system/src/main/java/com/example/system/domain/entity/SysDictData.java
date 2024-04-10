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
 * 字典数据表
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict_data")
@Schema(name = "DictData", description = "字典数据表")
public class SysDictData extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1533806736682892524L;
    /**
     * 显示顺序
     */
    @Schema(description = "显示顺序")
    private Integer sort;
    /**
     * 标签
     */
    @Schema(description = "标签")
    private String label;
    /**
     * 键值
     */
    @Schema(description = "键值")
    private String value;
    /**
     * 类型
     */
    @Schema(description = "类型")
    private String type;
    /**
     * 样式属性
     */
    @Schema(description = "样式属性")
    private String cssClass;
    /**
     * 表格回显样式
     */
    @Schema(description = "表格回显样式")
    private String listClass;
    /**
     * 是否默认(0否、1是)
     */
    @Schema(description = "是否默认(0否、1是)")
    private Integer isDefault;
}

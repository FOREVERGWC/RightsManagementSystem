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
 * 字典类型表
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict_type")
@Schema(name = "DictType", description = "字典类型表")
public class SysDictType extends BaseEntity {
    @Serial
    private static final long serialVersionUID = -1911478354246588813L;
    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;
    /**
     * 类型
     */
    @Schema(description = "类型")
    private String type;
}

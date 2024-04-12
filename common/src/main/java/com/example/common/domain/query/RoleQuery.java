package com.example.common.domain.query;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.domain.entity.SysRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
/**
 * <p>
 * 角色信息查询实体
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(name = "Role", description = "角色信息查询实体")
public class RoleQuery extends SysRole {
    @Serial
    private static final long serialVersionUID = -9108217950744766643L;
    /**
     * 页码
     */
    private Integer pageNo;
    /**
     * 页面大小
     */
    private Integer pageSize;
}

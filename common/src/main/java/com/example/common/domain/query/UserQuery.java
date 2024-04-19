package com.example.common.domain.query;

import com.example.common.domain.entity.SysUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * <p>
 * 用户信息查询实体
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(name = "User", description = "用户信息查询实体")
public class UserQuery extends SysUser {
    @Serial
    private static final long serialVersionUID = 220273724827491098L;
    /**
     * 页码
     */
    @Schema(description = "页码")
    private Integer pageNo;
    /**
     * 页面大小
     */
    @Schema(description = "页面大小")
    private Integer pageSize;
}

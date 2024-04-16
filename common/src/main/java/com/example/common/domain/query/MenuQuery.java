package com.example.common.domain.query;

import com.example.system.domain.entity.SysMenu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * <p>
 * 菜单查询实体
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(name = "Menu", description = "菜单查询实体")
public class MenuQuery extends SysMenu {
    @Serial
    private static final long serialVersionUID = 6669287146737434799L;
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

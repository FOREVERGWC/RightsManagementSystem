package com.example.common.domain.dto.edit;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 状态编辑实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@Schema(name = "状态编辑实体", description = "状态编辑实体")
public class StatusEditDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -8221264081507480806L;
    /**
     * 主键ID列表
     */
    @Schema(description = "主键ID列表")
    @NotEmpty(message = "{entity.ids.notEmpty}")
    private List<Long> ids;
    /**
     * 状态
     */
    @Schema(description = "状态")
    @NotNull(message = "{entity.status.notNull}")
    private Integer status;
}

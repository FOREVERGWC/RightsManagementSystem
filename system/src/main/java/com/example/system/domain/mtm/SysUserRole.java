package com.example.system.domain.mtm;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户和角色关联表
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@TableName("sys_user_role")
@Schema(name = "UserRole", description = "用户和角色关联表")
public class SysUserRole implements Serializable {
    @Serial
    private static final long serialVersionUID = -7430247206048348370L;
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;
    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private Long roleId;
}

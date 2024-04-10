package com.example.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统访问记录表
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@TableName("sys_login_info")
@Schema(name = "LoginInfo", description = "系统访问记录表")
public class SysLoginInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 8191632713498484668L;
    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;
    /**
     * 登录IP
     */
    @Schema(description = "登录IP")
    private String loginIp;
    /**
     * 登录时间
     */
    @Schema(description = "登录时间")
    private Date loginTime;
    /**
     * 登录地点
     */
    @Schema(description = "登录地点")
    private String loginLocation;
    /**
     * 浏览器类型
     */
    @Schema(description = "浏览器类型")
    private String browser;
    /**
     * 操作系统
     */
    @Schema(description = "操作系统")
    private String os;
    /**
     * 登录状态（0成功、1失败）
     */
    @Schema(description = "登录状态（0成功、1失败）")
    private Integer status;
    /**
     * 提示消息
     */
    @Schema(description = "提示消息")
    private String msg;
}

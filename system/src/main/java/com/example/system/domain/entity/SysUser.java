package com.example.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.system.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
@Schema(name = "User", description = "用户信息表")
public class SysUser extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 2503918656429754043L;
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;
    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;
    /**
     * 用户头像
     */
    @Schema(description = "用户头像")
    private String avatar;
    /**
     * 用户邮箱
     */
    @Schema(description = "用户邮箱")
    private String email;
    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称")
    private String nickname;
    /**
     * 用户性别（0女、1男、2未知）
     */
    @Schema(description = "用户性别（0女、1男、2未知）")
    private Integer gender;
    /**
     * 手机号码
     */
    @Schema(description = "手机号码")
    private String phone;
    /**
     * 最后登录IP
     */
    @Schema(description = "最后登录IP")
    private String loginIp;
    /**
     * 最后登录时间
     */
    @Schema(description = "最后登录时间")
    private Date loginTime;
}

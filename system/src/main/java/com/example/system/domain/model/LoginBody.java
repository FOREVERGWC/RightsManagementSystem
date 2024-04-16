package com.example.system.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

/**
 * 登录信息
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class LoginBody implements Serializable {
    @Serial
    private static final long serialVersionUID = -6888323393659836675L;
    /**
     * 用户名
     */
    @NotBlank(message = "{user.username.notBlank}")
    @Length(min = 2, max = 20, message = "{user.username.not.match}")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "{user.password.notBlank}")
    @Length(min = 5, max = 20, message = "{user.password.not.match}")
    private transient String password;
    /**
     * 验证码
     */
    private String code;
    /**
     * 唯一标识
     */
    private String uuid;
}

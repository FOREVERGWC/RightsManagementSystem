package com.example.system.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 登录信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class LoginBody implements Serializable {
    @Serial
    private static final long serialVersionUID = -6888323393659836675L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
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

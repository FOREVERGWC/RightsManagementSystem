package com.example.system.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 注册信息
 */
@Data
@SuperBuilder
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class RegisterBody extends LoginBody {
    @Serial
    private static final long serialVersionUID = -9134344341848461862L;
}

package com.example.common.exception;

import cn.hutool.core.util.StrUtil;
import com.example.common.utils.MessageUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

/**
 * 基本异常类
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -1019351295278177935L;

    /**
     * 所属模块
     */
    private String module;
    /**
     * 错误码对应的参数
     */
    private Object[] args;
    /**
     * 错误码
     */
    private String code;
    /**
     * 错误消息
     */
    private String msg;

    public BaseException(String module, Object[] args, String code) {
        this(module, args, code, null);
    }

    public BaseException(String module, String msg) {
        this(module, null, null, msg);
    }

    public BaseException(Object[] args, String code) {
        this(null, args, code, null);
    }

    public BaseException(String msg) {
        this(null, null, null, msg);
    }

    @Override
    public String getMessage() {
        String msg = null;
        if (StrUtil.isNotBlank(code)) {
            msg = MessageUtils.getMsg(code, args);
        }
        if (msg == null) {
            msg = this.msg;
        }
        return msg;
    }
}

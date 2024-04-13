package com.example.common.annotation;

import com.example.common.enums.BusinessType;
import com.example.common.enums.OperatorType;

import java.lang.annotation.*;

/**
 * 操作日志注解
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块
     * @return 字符串
     */
    String title() default "";
    /**
     * 功能
     * @return 业务操作类型
     */
    BusinessType businessType() default BusinessType.OTHER;
    /**
     * 操作人类别
     * @return 操作人类别
     */
    OperatorType operatorType() default OperatorType.MANAGE;
    /**
     * 是否保存请求的参数
     * @return 布尔值
     */
    boolean isSaveRequestData() default true;
    /**
     * 是否保存响应的参数
     * @return 布尔值
     */
    boolean isSaveResponseData() default true;
    /**
     * 排除指定的请求参数
     * @return 字符串数组
     */
    String[] excludeParamNames() default {};
}

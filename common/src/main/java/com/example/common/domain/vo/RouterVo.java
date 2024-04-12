package com.example.common.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 路由配置信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RouterVo implements Serializable {
    @Serial
    private static final long serialVersionUID = -1146954902638054331L;
    /**
     * 路由名字
     */
    private String name;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 是否隐藏路由
     */
    private boolean hidden;
    /**
     * 重定向地址
     */
    private String redirect;
    /**
     * 组件地址
     */
    private String component;
    /**
     * 是否总是可见
     */
    private Boolean alwaysShow;
    /**
     * 其他元素
     */
    private MetaVo meta;
    /**
     * 子路由
     */
    private List<RouterVo> children;
}

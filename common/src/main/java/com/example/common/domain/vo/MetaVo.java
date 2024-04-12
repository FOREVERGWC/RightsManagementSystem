package com.example.common.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 路由显示信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MetaVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 5942593149548508630L;
    /**
     * 路由名称
     */
    private String title;
    /**
     * 路由图标
     */
    private String icon;
    /**
     * 是否缓存（0否、1是）
     */
    private Integer noCache;
    /**
     * 内链地址
     */
    private String link;

    public MetaVo(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

    public MetaVo(String title, String icon, String link) {
        this.title = title;
        this.icon = icon;
        this.link = link;
    }
}

package com.example.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.common.utils.UserUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自定义元数据对象处理器
 */
@Component
public class AutoFillMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入填充
     *
     * @param metaObject 元数据
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue("createBy", metaObject.getValue("createBy") != null ? metaObject.getValue("createBy") : UserUtils.getLoginUsername());
        metaObject.setValue("createTime", new Date());
    }

    /**
     * 更新填充
     *
     * @param metaObject 元数据
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateBy", UserUtils.getLoginUsername());
        metaObject.setValue("updateTime", new Date());
    }
}

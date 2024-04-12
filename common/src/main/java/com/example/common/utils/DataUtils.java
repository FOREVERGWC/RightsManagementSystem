package com.example.common.utils;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataUtils {
    /**
     * 列表对象转树形对象
     *
     * @param list          列表对象
     * @param parentIdField 父级ID字段
     * @param childrenField 子级字段
     * @param idField       ID字段
     * @param parentId      父级ID
     * @param <T>           入参泛型
     * @param <U>           父级ID类型
     * @return 子级列表
     */
    public static <T, U> List<T> listToTree(List<T> list, Function<T, U> parentIdField, Setter<T, List<T>> childrenField, Function<T, U> idField, U parentId) {
        if (Objects.isNull(list)) {
            return null;
        }
        List<T> children = list.stream().filter(data -> Objects.equals(parentIdField.apply(data), parentId)).collect(Collectors.toList());
        children.forEach(child -> childrenField.accept(child, listToTree(list, parentIdField, childrenField, idField, idField.apply(child))));
        return children;
    }

    @FunctionalInterface
    public interface Setter<T, P> {
        void accept(T t, P p);
    }
}

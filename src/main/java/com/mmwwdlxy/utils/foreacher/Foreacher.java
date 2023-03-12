package com.mmwwdlxy.utils.foreacher;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 1、从一段数据源中获取数据
 * 2、遍历数据执行单条逻辑
 */
public class Foreacher<T> implements Foreach<T> {

    @Override
    public void doLogic(Function<Integer, List<T>> resource, Consumer<T> logic) {

        // bad case
        if (resource == null || logic == null) {
            return;
        }

        int offset = 0;
        List<T> list;

        while ((list = resource.apply(offset++)) != null && !list.isEmpty()){
            list.forEach(logic);
        }
    }

}

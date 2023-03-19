package com.mmwwdlxy.utils.designpattern.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * 建造者
 */
@FunctionalInterface
public interface Builder<T> {

    void item(Function<Object, Builder<T>> function);

    default T build(Function<Builder<T>, T> function) {
        return function.apply(this);
    }

    class DefaultBuilder<T> implements Builder<T> {

        private final List<Function<Object, Builder<T>>> functions = new ArrayList<>();

        @Override
        public void item(Function<Object, Builder<T>> function) {
            functions.add(function);
        }

        @Override
        public T build(Function<Builder<T>, T> function) {
            T t = function.apply(this);
            for (Function<Object, Builder<T>> f : functions) {
                f.apply(t);
            }
            return t;
        }
    }
}

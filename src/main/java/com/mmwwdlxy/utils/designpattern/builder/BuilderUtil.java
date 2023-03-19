package com.mmwwdlxy.utils.designpattern.builder;

import java.util.function.Function;

public class BuilderUtil {

    public static <T> Builder<T> createBuilder() {
        return new Builder.DefaultBuilder<>();
    }

    public static <T> void item(Function<Object, Builder<T>> function, Builder<T> builder) {
        builder.item(function);
    }

    public static <T> T build(Function<Builder<T>, T> function, Builder<T> builder) {
        return builder.build(function);
    }
}

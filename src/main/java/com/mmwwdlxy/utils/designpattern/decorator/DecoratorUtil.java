package com.mmwwdlxy.utils.designpattern.decorator;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class DecoratorUtil<T, R> {

    public static <T, R> Decorator<T, R> decorate(Function<T, R> function, Consumer<T> before, BiFunction<R, T, R> after) {
        return (t) -> {
            before.accept(t);
            return after.apply(function.apply(t), t);
        };
    }

    public static <T, R> R execute(Decorator<T, R> decorator, T t) {
        return decorator.execute(t);
    }
}

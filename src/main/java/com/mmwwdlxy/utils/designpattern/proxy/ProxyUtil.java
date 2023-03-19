package com.mmwwdlxy.utils.designpattern.proxy;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ProxyUtil {

    public static <T, R> Proxy<R> proxy(Supplier<T> supplier, Function<T, R> function, Consumer<T> before, BiFunction<R, T, R> after) {
        return () -> {
            T t = supplier.get();
            before.accept(t);
            return after.apply(function.apply(t), t);
        };
    }

    public static <R> R execute(Proxy<R> proxy) {
        return proxy.execute();
    }

}

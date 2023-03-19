package com.mmwwdlxy.utils.designpattern.expand.proxy;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ProxyUtil {

    /**
     * 仅表达扩展意义
     */
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

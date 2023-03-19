package com.mmwwdlxy.utils.designpattern.prototype;

import java.util.function.Function;

/**
 * 原型模式
 */
public class PrototypeUtil {

    public static <T> T create(Prototype<T> prototype, T t) {
        return prototype.create(t);
    }

    public static <T> T create(Function<T, T> function, T t) {
        return function.apply(t);
    }
}

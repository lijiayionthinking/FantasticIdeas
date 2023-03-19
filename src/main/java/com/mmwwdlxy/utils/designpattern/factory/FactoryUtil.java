package com.mmwwdlxy.utils.designpattern.factory;

import java.util.function.Function;

/**
 * 工厂模式
 */
public class FactoryUtil {

    public static <T> T produce(AbstractFactory<T> factory) {
        return factory.produce();
    }

    public static <T1, T> T produce(Function<T1, T> factory, T1 t1) {
        return factory.apply(t1);
    }
}

package com.mmwwdlxy.utils.designpattern.singleton;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 单例dcl
 */
public class SingletonUtil {

    private static final Map<Class<?>, Object> singletonMap = new ConcurrentHashMap<>();

    public static <T> T getSingleton(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (singletonMap.containsKey(clazz)) {
            return (T) singletonMap.get(clazz);
        }
        synchronized (clazz) {
            if (singletonMap.containsKey(clazz)) {
                return (T) singletonMap.get(clazz);
            }

            return (T) singletonMap.put(clazz, clazz.getDeclaredConstructor().newInstance());
        }
    }

    public static <T1, T> T getSingleton(Class<?> clazz, Function<T1, T> function, T1 t1) {
        if (singletonMap.containsKey(clazz)) {
            return (T) singletonMap.get(clazz);
        }
        synchronized (clazz) {
            if (singletonMap.containsKey(clazz)) {
                return (T) singletonMap.get(clazz);
            }

            return (T) singletonMap.put(clazz, function.apply(t1));
        }
    }

    public static <T> T getSingleton(Class<?> clazz, Supplier<T> supplier) {
        if (singletonMap.containsKey(clazz)) {
            return (T) singletonMap.get(clazz);
        }
        synchronized (clazz) {
            if (singletonMap.containsKey(clazz)) {
                return (T) singletonMap.get(clazz);
            }

            return (T) singletonMap.put(clazz, supplier.get());
        }
    }
}

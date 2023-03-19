package com.mmwwdlxy.utils.designpattern.expand.adapter;

import java.util.function.BiFunction;
import java.util.function.Function;

public class AdapterUtil<T, R> {

    /**
     * 仅表达扩展意义,编排仅展示某种可能
     */
    public static <T, T1, R, R1> Adapter<T, R1> adapter(Function<T, R> f, Function<T1, R1> fAdapter, BiFunction<T, R, T1> fConvert) {
        return (t) -> fAdapter.apply(fConvert.apply(t, f.apply(t)));
    }

    public static <T, R> R execute(Adapter<T, R> adapter, T t) {
        return adapter.execute(t);
    }
}

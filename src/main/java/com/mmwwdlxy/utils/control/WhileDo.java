package com.mmwwdlxy.utils.control;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * while循环函数式版
 * 最后一定会执行一次
 */
public class WhileDo<T> {
    private Function<T, Boolean> condition;
    private Consumer<T> logic;

    private WhileDo() {

    }

    public static <T> WhileDo<T> needOne() {
        return new WhileDo<>();
    }

    public static <T> WhileDo<T> needOne(Function<T, Boolean> condition, Consumer<T> logic) {
        WhileDo<T> While = new WhileDo<>();
        While.condition = condition;
        While.logic = logic;
        return While;
    }

    public WhileDo<T> cond(Function<T, Boolean> condition) {
        return condition(condition);
    }

    public WhileDo<T> condition(Function<T, Boolean> condition) {
        this.condition = condition;
        return this;
    }

    public WhileDo<T> logic(Consumer<T> logic) {
        this.logic = logic;
        return this;
    }

    public void start(T t) {
        // bad case
        if (logic == null || condition == null) {
            throw new NullPointerException();
        }

        final Function<T, Boolean> fCondition = condition;
        final Consumer<T> fLogic = logic;

        try {
            while (fCondition.apply(t)) {
                fLogic.accept(t);
            }
        } finally {
            fLogic.accept(t);
        }

    }

    public void start() {
        start(null);
    }
}

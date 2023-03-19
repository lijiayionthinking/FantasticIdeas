package com.mmwwdlxy.utils.control;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * do while循环函数式版
 * 最后一定会执行一次
 */
public class DoWhileDo<T> {
    private Function<T, Boolean> condition;
    private Consumer<T> logic;

    private DoWhileDo() {

    }

    public static <T> DoWhileDo<T> needOne() {
        return new DoWhileDo<>();
    }

    public static <T> DoWhileDo<T> needOne(Function<T, Boolean> condition, Consumer<T> logic) {
        DoWhileDo<T> While = new DoWhileDo<>();
        While.condition = condition;
        While.logic = logic;
        return While;
    }

    public DoWhileDo<T> cond(Function<T, Boolean> condition) {
        return condition(condition);
    }

    public DoWhileDo<T> condition(Function<T, Boolean> condition) {
        this.condition = condition;
        return this;
    }

    public DoWhileDo<T> logic(Consumer<T> logic) {
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
            do {
                fLogic.accept(t);
            } while (fCondition.apply(t));
        } finally {
            fLogic.accept(t);
        }

    }

    public void start() {
        start(null);
    }
}

package com.mmwwdlxy.utils.control;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * while循环函数式版
 */
public class While<T> {
    private Function<T, Boolean> condition;
    private Consumer<T> logic;

    private While() {

    }

    public static <T> While<T> needOne() {
        return new While<>();
    }

    public static <T> While<T> needOne(Function<T, Boolean> condition, Consumer<T> logic) {
        While<T> While = new While<>();
        While.condition = condition;
        While.logic = logic;
        return While;
    }

    public While<T> cond(Function<T, Boolean> condition) {
        return condition(condition);
    }

    public While<T> condition(Function<T, Boolean> condition) {
        this.condition = condition;
        return this;
    }

    public While<T> logic(Consumer<T> logic) {
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

        while (fCondition.apply(t)) {
            fLogic.accept(t);
        }

    }

    public void start() {
        start(null);
    }
}

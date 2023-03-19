package com.mmwwdlxy.utils.control;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * do while循环函数式版
 */
public class DoWhile<T> {
    private Function<T, Boolean> condition;
    private Consumer<T> logic;

    private DoWhile() {

    }

    public static <T> DoWhile<T> needOne() {
        return new DoWhile<>();
    }

    public static <T> DoWhile<T> needOne(Function<T, Boolean> condition, Consumer<T> logic) {
        DoWhile<T> While = new DoWhile<>();
        While.condition = condition;
        While.logic = logic;
        return While;
    }

    public DoWhile<T> cond(Function<T, Boolean> condition) {
        return condition(condition);
    }

    public DoWhile<T> condition(Function<T, Boolean> condition) {
        this.condition = condition;
        return this;
    }

    public DoWhile<T> logic(Consumer<T> logic) {
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

        do{
            fLogic.accept(t);
        } while (fCondition.apply(t));

    }

    public void start() {
        start(null);
    }
}

package com.mmwwdlxy.utils;

import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 直到条件成立方执行一次逻辑
 * 到达最大次数退出
 */
public class Until<T> {

    private Function<T, Boolean> condition;
    private Consumer<T> logic;

    private final TimerTask emptyTask = new TimerTask() {
        @Override
        public void run() {

        }
    };

    private Until() {

    }

    public static <T> Until<T> needOne() {
        return new Until<>();
    }

    public static <T> Until<T> needOne(Function<T, Boolean> condition, Consumer<T> logic) {
        Until<T> until = new Until<>();
        until.condition = condition;
        until.logic = logic;
        return until;
    }

    public Until<T> until(Function<T, Boolean> condition) {
        this.condition = condition;
        return this;
    }

    public Until<T> logic(Consumer<T> logic) {
        this.logic = logic;
        return this;
    }

    public void start(long interval, int maxTimes) {
        start(null, interval, maxTimes);
    }

    public void start(T t, long interval, int maxTryTimes) {
        // bad case
        if (logic == null || condition == null) {
            throw new NullPointerException();
        }

        final Function<T, Boolean> fCondition = condition;
        final Consumer<T> fLogic = logic;

        int count = 0;
        Timer timer = new Timer();

        while (!fCondition.apply(t)) {
            if (++count > maxTryTimes) {
                return;
            }
            timer.schedule(emptyTask, interval);
        }

        fLogic.accept(t);
    }
}

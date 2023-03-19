package com.mmwwdlxy.utils.control;

import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 自从条件成立方执行n次逻辑
 * 到达尝试最大次数退出
 * 到达执行最大次数退出
 */
public class Since<T> {

    private Function<T, Boolean> condition;
    private Consumer<T> logic;

    private final TimerTask emptyTask = new TimerTask() {
        @Override
        public void run() {

        }
    };

    private Since() {

    }

    public static <T> Since<T> needOne() {
        return new Since<>();
    }

    public static <T> Since<T> needOne(Function<T, Boolean> condition, Consumer<T> logic) {
        Since<T> until = new Since<>();
        until.condition = condition;
        until.logic = logic;
        return until;
    }

    public Since<T> since(Function<T, Boolean> condition) {
        this.condition = condition;
        return this;
    }

    public Since<T> logic(Consumer<T> logic) {
        this.logic = logic;
        return this;
    }

    public void start(long interval, int maxTryTimes, int maxDoTimes) {
        start(null, interval, maxTryTimes, maxDoTimes);
    }

    public void start(T t, long interval, int maxTryTimes, int maxDoTimes) {
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
        count = 1;
        while (!fCondition.apply(t)) {
            if (++count > maxDoTimes) {
                return;
            }
            timer.schedule(emptyTask, interval);
            fLogic.accept(t);
        }

    }
}

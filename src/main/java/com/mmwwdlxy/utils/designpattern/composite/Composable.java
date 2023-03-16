package com.mmwwdlxy.utils.designpattern.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 可组合的接口，为组合模式类设计，泛型是为了使用丝滑一些, 但是组合时不优雅效果不佳
 */
public interface Composable<T> {

    void add(Composable<T> sub);

    void remove(Composable<T> sub);

    Collection<Composable<T>> getSubs();

    void set(T t);

    T get();

    class Composed<T> implements Composable<T> {

        private final List<Composable<T>> list = new ArrayList<>();

        private T t;

        public static <T> Composed<T> create(T t) {
            Composed<T> composed = new Composed<>();
            composed.t = t;
            return composed;
        }

        @Override
        public void add(Composable<T> sub) {
            list.add(sub);
        }

        @Override
        public void remove(Composable<T> sub) {
            list.remove(sub);
        }

        @Override
        public Collection<Composable<T>> getSubs() {
            return list;
        }

        @Override
        public void set(T t) {
            this.t = t;
        }

        @Override
        public T get() {
            return t;
        }
    }
}

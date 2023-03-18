package com.mmwwdlxy.utils.designpattern.memento;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 备忘录模式持有者，key存取方式
 */
public interface KeyCaretaker<K, T> {

    T getMemento(K k);

    void setMemento(K k, T t);

    /**
     * hashmap版，使用concurrent是因为个人认为此方法应该多数用于多线程
     */
    class MapCaretaker<K, T> implements KeyCaretaker<K, T> {

        private final Map<K, T> memento = new ConcurrentHashMap<>();

        @Override
        public T getMemento(K k) {
            return memento.get(k);
        }

        @Override
        public void setMemento(K k, T t) {
            memento.put(k, t);
        }
    }

    /**
     * list版，实现欠佳，不如数组
     */
    class ListCaretaker<T> implements KeyCaretaker<Integer, T> {

        private final List<T> memento = new Vector<>();

        @Override
        public T getMemento(Integer integer) {
            return memento.get(integer);
        }

        @Override
        public void setMemento(Integer integer, T t) {
            memento.add(integer, t);
        }
    }
}

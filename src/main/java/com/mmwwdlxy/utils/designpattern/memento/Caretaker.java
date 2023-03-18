package com.mmwwdlxy.utils.designpattern.memento;

/**
 * 备忘录模式持有者
 */
public interface Caretaker<T> {

    T getMemento();

    void setMemento(T t);

    class DefaultCaretaker<T> implements Caretaker<T> {

        private T t;

        @Override
        public T getMemento() {
            if (t == null) {
                throw new NullPointerException("DefaultCaretaker memento is null");
            }
            return t;
        }

        @Override
        public void setMemento(T t) {
            this.t = t;
        }
    }
}

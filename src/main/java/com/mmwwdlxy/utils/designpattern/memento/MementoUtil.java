package com.mmwwdlxy.utils.designpattern.memento;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * 备忘录模式函数式版工具类
 * 未经验证
 */
public class MementoUtil {

    public static <T> Caretaker<T> createMemento() {
        return new Caretaker.DefaultCaretaker<>();
    }

    public static <K, T> KeyCaretaker<K, T> createKeyMemento() {
        return new KeyCaretaker.MapCaretaker<>();
    }

    public static <T> KeyCaretaker<Integer, T> createListMemento() {
        return new KeyCaretaker.ListCaretaker<>();
    }

    public static <T, T1> void save(Caretaker<T> caretaker, T1 t1, Function<T1, T> f) {
        caretaker.setMemento(f.apply(t1));
    }

    public static <T, T1> void recover(Caretaker<T> caretaker, T1 t1, BiConsumer<T1, T> bic) {
        bic.accept(t1, caretaker.getMemento());
    }

    public static <T> void save(Caretaker<T> caretaker, Originator<T> t1, Originator<T> originator) {
        caretaker.setMemento(originator.getSave().apply(t1));
    }

    public static <T> void recover(Caretaker<T> caretaker, Originator<T> t1, Originator<T> originator) {
        originator.getRecover().accept(t1, caretaker.getMemento());
    }

    public static <K, T, T1> void save(KeyCaretaker<K, T> caretaker, K k, T1 t1, Function<T1, T> f) {
        caretaker.setMemento(k, f.apply(t1));
    }

    public static <K, T, T1> void recover(KeyCaretaker<K, T> caretaker, K k, T1 t1, BiConsumer<T1, T> bic) {
        bic.accept(t1, caretaker.getMemento(k));
    }
}

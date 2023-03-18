package com.mmwwdlxy.utils.designpattern.memento;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * 发起人抽象，或许抽象类更好
 */
public interface Originator<T> {

    Function<Originator<T>, T> getSave();

    BiConsumer<Originator<T>, T> getRecover();
}

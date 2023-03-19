package com.mmwwdlxy.utils.designpattern.prototype;

@FunctionalInterface
public interface Prototype<T> {

    T create(T t);
}

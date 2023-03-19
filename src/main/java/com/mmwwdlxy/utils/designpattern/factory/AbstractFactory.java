package com.mmwwdlxy.utils.designpattern.factory;

@FunctionalInterface
public interface AbstractFactory<T> {

    T produce();
}

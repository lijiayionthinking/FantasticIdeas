package com.mmwwdlxy.utils.designpattern.expand.decorator;

/**
 * 装饰，类结构语法原因无法拟真
 */
public interface Decorator<T, R> {

    R execute(T t);
}

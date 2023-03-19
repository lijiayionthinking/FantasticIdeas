package com.mmwwdlxy.utils.designpattern.expand.proxy;

/**
 * 代理，类结构语法原因无法拟真
 */
@FunctionalInterface
public interface Proxy<T> {

    T execute();
}

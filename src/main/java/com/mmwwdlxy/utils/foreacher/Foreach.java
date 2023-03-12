package com.mmwwdlxy.utils.foreacher;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Foreach<T> {

    void doLogic(Function<Integer, List<T>> resource, Consumer<T> logic);

}

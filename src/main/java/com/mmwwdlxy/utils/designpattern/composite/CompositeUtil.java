package com.mmwwdlxy.utils.designpattern.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 组合模式函数式版工具类，未验证
 */
public class CompositeUtil {

    public static <T, R, FR> FR execute(Function<T, R> function, Function<List<R>, FR> aggregation, Composable<T> combined) {
        List<R> resultList = new ArrayList<>();
        if (combined.getSubs() != null && !combined.getSubs().isEmpty()) {
            for (Composable<T> tComposable : combined.getSubs()) {
                execute(function, aggregation, tComposable);
            }
        } else {
            resultList.add(function.apply(combined.get()));
        }
        return aggregation.apply(resultList);
    }

    public static <T> void execute(Consumer<T> consumer, Composable<T> combined) {
        if (combined.getSubs() != null && !combined.getSubs().isEmpty()) {
            for (Composable<T> tComposable : combined.getSubs()) {
                execute(consumer, tComposable);
            }
        } else {
            consumer.accept(combined.get());
        }
    }

    public static <T> Composable<T> compose(Supplier<Collection<T>> supplier) {
        Collection<T> collection = supplier.get();
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        Composable<T> composable = new Composable.Composed<>();
        for (T t : collection) {
            composable.add(Composable.Composed.create(t));
        }
        return composable;
    }

    public static <T> Composable<T> compose(Composable<T> composable, Supplier<Collection<T>> supplier) {
        Collection<T> collection = supplier.get();
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        for (T t : collection) {
            composable.add(Composable.Composed.create(t));
        }
        return composable;
    }

    public static <T> Composable<T> compose(Function<T, Collection<T>> function, T t) {
        return compose(null, function, t);
    }

    public static <T> Composable<T> compose(Composable<T> composable, Function<T, Collection<T>> function, T t) {
        Collection<T> collection = function.apply(t);
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        composable = composable == null ? new Composable.Composed<>() : composable;
        for (T tt : collection) {
            Composable<T> subComposable = compose(composable, function, tt);
            if (subComposable != null) {
                composable.add(subComposable);
            } else {
                composable.add(Composable.Composed.create(tt));
            }
        }
        return composable;
    }

}

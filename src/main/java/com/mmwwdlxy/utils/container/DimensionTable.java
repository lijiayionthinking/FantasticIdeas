package com.mmwwdlxy.utils.container;

import java.util.ArrayList;

/**
 * 二维数组组成的二维表
 * 通过横纵坐标存放获取数据
 * 简陋的实现，不建议除看看以外的一切使用
 */
public class DimensionTable<T> {

    protected final ArrayList<ArrayList<T>> buckets;

    public DimensionTable() {
        buckets = new ArrayList<>();
    }

    public DimensionTable(int bucketSize) {
        buckets = new ArrayList<>(bucketSize);
    }

    public void put(int x, int y, T value){
        ArrayList<T> ts = buckets.get(x);
        if(ts == null){
            buckets.add(x,  ts = new ArrayList<>());
        }
        ts.add(y, value);
    }

    public T get(int x, int y){
        return buckets.get(x).get(y);
    }

}

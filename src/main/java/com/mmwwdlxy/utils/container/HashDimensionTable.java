package com.mmwwdlxy.utils.container;

import java.util.ArrayList;

/**
 * 二维数组组成的hash二维表
 * 通过横纵坐标存放获取数据，其中横坐标通过key的hash值取模得来
 * 极其简陋的实现，不建议除随便看看以外的一切使用
 */
public class HashDimensionTable<K, V> extends DimensionTable<V> {

    private final int bucketSize;

    public HashDimensionTable() {
        bucketSize = 16;
    }

    public HashDimensionTable(int bucketSize) {
        super(bucketSize);
        this.bucketSize = bucketSize;
    }

    public void put(K key, int y, V value) {
        int x = key.hashCode() % bucketSize;
        ArrayList<V> ts = buckets.get(x);
        if (ts == null) {
            buckets.add(x, ts = new ArrayList<>());
        }
        ts.add(y, value);
    }

    public V get(K key, int y) {
        return buckets.get(key.hashCode() % bucketSize).get(y);
    }
}

package com.mmwwdlxy.utils.container;

import java.util.ArrayList;

/**
 * 二维数组组成的hash二维表
 * 通过横纵坐标存放获取数据，其中横坐标通过key的hash值取模得来
 * 极其简陋的实现，不建议除随便看看以外的一切使用
 * 未经任何验证
 */
public class DimensionHashMap<K, V> extends DimensionTable<V> {

    private final int bucketSize;

    public DimensionHashMap() {
        bucketSize = 16;
    }

    public DimensionHashMap(int bucketSize) {
        super(bucketSize);
        this.bucketSize = bucketSize;
    }

    public int put(K key, V value) {
        int x = key.hashCode() % bucketSize;
        ArrayList<V> ts = buckets.get(x);
        if (ts == null) {
            buckets.add(x, ts = new ArrayList<>());
        }
        for (int i = 0; i< ts.size(); i++) {
            if (ts.get(i) == null) {
                ts.add(i, value);
                return i;
            }
        }
        ts.add(value);
        return ts.size();
    }

    public V get(K key, int y) {
        return buckets.get(key.hashCode() % bucketSize).get(y);
    }
}

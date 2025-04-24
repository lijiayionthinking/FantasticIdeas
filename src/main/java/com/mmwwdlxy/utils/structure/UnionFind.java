package com.mmwwdlxy.utils.structure;

import java.util.Arrays;

/**
 * 并查集 负数表示法 树高合并法
 */
public class UnionFind {
    private final int[] p;
    private int n;

    public UnionFind(int n) {
        this.n = n;
        p = new int[n];
        Arrays.fill(p, -1);
    }

    public int find(int x) {
        if (p[x] < 0) {
            return x;
        }
        return p[x] = find(p[x]);
    }

    public void union(int a, int b) {
        int r1 = find(a);
        int r2 = find(b);
        if (r1 == r2) {
            return;
        }
        n--;
        if (p[r1] >= p[r2]) {
            p[r1] = r2;
            p[r2]--;
        } else {
            p[r2] = r1;
            p[r1]--;
        }
    }

    public int getCount() {
        return n;
    }
}

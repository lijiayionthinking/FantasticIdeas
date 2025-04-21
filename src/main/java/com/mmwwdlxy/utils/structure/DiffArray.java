package com.mmwwdlxy.utils.structure;

/**
 * 差分数组
 */
public class DiffArray {

    private final int[] difArr;
    private boolean isBack = false;

    public DiffArray(int len) throws Exception {
        if (len < 0) {
            throw new Exception("param not legal");
        }
        difArr = new int[len];
    }

    public void rangeChange(int index, int val) throws Exception {
        if (isBack) {
            throw new Exception("can not change after get()");
        }
        difArr[index] += val;
    }

    private void backOriginal() {
        isBack = true;
        int sum = 0;
        for (int i = 0; i < difArr.length; i++) {
            sum += difArr[i];
            difArr[i] = sum;
        }
    }

    public int get(int index) throws Exception {
        if (index < 0 || index >= difArr.length) {
            throw new Exception("param not legal");
        }
        if (!isBack) {
            backOriginal();
        }
        return difArr[index];
    }
}

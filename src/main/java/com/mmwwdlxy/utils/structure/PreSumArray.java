package com.mmwwdlxy.utils.structure;

/**
 * 前缀和
 */
public class PreSumArray {

    private final int[] sumArr;

    public PreSumArray(int[] array) throws Exception {
        if (array == null || array.length == 0) {
            throw new Exception("important param is empty");
        }
        sumArr = new int[array.length];
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            sumArr[i] = sum;
        }
    }

    /**
     * 左闭右闭
     *
     * @param l
     * @param r
     * @return rangeSum[l, r]
     * @throws Exception
     */
    public int rangeSum(int l, int r) throws Exception {
        if (l < 0 || l > r || r >= sumArr.length) {
            throw new Exception("param not legal");
        }
        if (l != 0) {
            return sumArr[r] - sumArr[l - 1];
        }
        return sumArr[r];
    }
}

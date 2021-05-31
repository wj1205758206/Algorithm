package sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] nums = {6, 5, 3, 8, 1, 7, 2, 9, 4};
        segment(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 递归切分待排
     *
     * @param nums  待切分数组
     * @param left  待切分最后第一个元素的索引
     * @param right 待切分数组最后一个元素的索引
     */
    private static void segment(int[] nums, int left, int right) {
        if (left < right) {
            // 找出中间索引
            int center = (left + right) / 2;
            // 对左边数组进行递归
            segment(nums, left, center);
            // 对右边数组进行递归
            segment(nums, center + 1, right);
            // 合并
            merge(nums, left, center, right);
        }
    }

    /**
     * 两两归并排好序的数组（2路归并）
     *
     * @param nums   带排序数组对象
     * @param left   左边数组的第一个索引
     * @param center 左数组的最后一个索引，center + 1右数组的第一个索引
     * @param right  右数组的最后一个索引
     */
    private static void merge(int[] nums, int left, int center, int right) {
        int[] tmpArray = new int[right - left + 1];
        int leftIndex = left;   //左数组第一个元素的索引
        int rightIndex = center + 1;   //右数组第一个元素索引
        int tmpIndex = 0;    //临时数组索引

        // 把较小的数先移到新数组中
        while (leftIndex <= center && rightIndex <= right) {
            if (nums[leftIndex] <= nums[rightIndex]) {
                tmpArray[tmpIndex++] = nums[leftIndex++];
            } else {
                tmpArray[tmpIndex++] = nums[rightIndex++];
            }
        }

        // 如果左边有剩余，就直接把左边的数移入数组
        while (leftIndex <= center) {
            tmpArray[tmpIndex++] = nums[leftIndex++];
        }

        // 如果右边的有剩余，就直接把右变 的数移入数组
        while (rightIndex <= right) {
            tmpArray[tmpIndex++] = nums[rightIndex++];
        }

        // 把新数组中的数覆盖nums数组
    /*for (int i = 0; i < tmpArray.length; i++) {
        nums[begin + i] = tmpArray[i];
    }*/
        //可以优化成下面的写法
        System.arraycopy(tmpArray, 0, nums, left, tmpArray.length);
    }
}

package com.example.demo.leetcode;

import java.util.Arrays;

/**
 * 给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，
 * 使得将数组中所有大于 value 的值变成 value 后，数组的和最接近  target （最接近表示两者之差的绝对值最小）。
 * <p>
 * 如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。
 * <p>
 * 请注意，答案不一定是 arr 中的数字。
 * <p>
 * 输入：arr = [4,9,3], target = 10
 * 输出：3
 * 解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
 * <p>
 * 输入：arr = [2,3,5], target = 10
 * 输出：5
 * <p>
 * 输入：arr = [60864,25176,27249,21296,20204], target = 56803
 * 输出：11361
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-mutated-array-closest-to-target
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject1300 {
    public static void main(String[] args) {
        System.out.println(11360 * 5);
        System.out.println(11361 * 5);
        System.out.println(11362 * 5);
        System.out.println(56803 / 5);
        System.out.println(findBestValue(new int[]{60864, 25176, 27249, 21296, 20204}, 56803));
    }

    public static int findBestValue(int[] arr, int target) {
        if (arr.length > Math.pow(10, 4) + 1 || arr.length == 0) return 0;
        if (target > Math.pow(10, 5) + 1) return 0;
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        if (arr[0] < 1) return 0;
        if (arr[0] - target / arr.length > arr.length) {//值不在arr中
            int avgTarget = target / arr.length;
            for (int i = 0; i < arr.length; i++) {
                if ((avgTarget + i) * arr.length < target && (avgTarget + i + 1) * arr.length > target) {
                    return target - (avgTarget + i) * arr.length <= (avgTarget + i + 1) * arr.length - target ? avgTarget + i : avgTarget + i + 1;
                }
            }

        }

        for (int i = 0; i < arr.length; i++) {
//            if(arr[i]*arr.length)
        }
        return 0;
    }

}

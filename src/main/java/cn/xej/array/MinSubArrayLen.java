package cn.xej.array;

/**
 * 题目描述
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其总和大于等于target的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 *
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 *
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */

public class MinSubArrayLen {

    /// 方法双指针
    /// 因为nums中的元素都是正整数，可以考虑使用双指针来维护一个滑动窗口
    /// 定义2个指针l和r，用一个变量s代表滑动窗口中的元素和
    /// 每一步操作中，我们移动右指针r，使得滑动窗口中加入一个元素，如果此时s>=target
    /// 我们就更新最小长度res = min(res,l-r+1),并将左指针l循环向右移动，直至s<target
    public static void main(String[] args) {
        int[] nums = new int[]{1,4,4};
        System.out.println(minSubArrayLen(nums, 4));
    }

    public static int minSubArrayLen(int[] nums, int target) {
        int res = nums.length + 1;
        int s = 0;
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            s += nums[j];
            ///while是核心，主要是将滑动窗口的左指针向右移动
            while (s >= target) {
                int len = j - i + 1;
                res = Math.min(res, len);
                s -= nums[i];
                i++;
            }
        }
        return res > nums.length ? 0 : res;
    }
}

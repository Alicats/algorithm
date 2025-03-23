package cn.xej.array;

/**
 * 题目描述
 * 峰值元素是指其值严格大于左右相邻值的元素。
 *
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 *
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 *
 * 你必须实现时间复杂度为 O(log n)的算法来解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2：
 *
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 * 解释：你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * -231 <= nums[i] <= 231 - 1
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 */

public class PeekSearch {

    /// 二分查找
    /// 定义2个指针l、r，在每一步二分查找中，比较mid与其右边元素mid+1的值
    /// 如果mid的值大于mid+1，则表示此时mid为下坡路，那么自己本身可能就是山峰或下山过程中，因此r=mid
    /// 如果mid的值小于mid+1，则表示此时mid为上坡路，因此l=mid+1
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,1,3,5,6,4};
        System.out.println(peekSearch(nums));
    }

    public static int peekSearch(int[] nums) {
        //二分查找
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid+1]) {
                //说明此时mid为下坡路，那么有可能自己本身就是山峰，或者在下山的过程中，所以right=mid而不能等于mid-1
                r = mid;
            } else {
                //反之说明此时mid为上坡路，既然是上坡，那么mid肯定不是山峰，所以left=mid+1（题目要求nums[i]!=nums[i+1]，所以不可能存在“平峰”的情况）
                l = mid + 1;
            }
        }
        return l;
    }
}

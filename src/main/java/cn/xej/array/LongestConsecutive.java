package cn.xej.array;

import java.util.HashSet;
import java.util.Set;


/**
 * 题目描述
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为 O(n)的算法解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 *
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class LongestConsecutive {

    /// 哈希表
    /// 用哈希表存储数组中的所有元素，遍历数组中的每个元素x，如果当前元素的前驱x-1不在哈希表中
    /// 那边我们就以当前元素为起点，不断尝试匹配x+1,x+1,x+3... 直到匹配不到为止
    public static void main(String[] args) {
        int[] nums = new int[]{100,4,200,1,3,2};
        int count = longestConsecutive(nums);
        System.out.println(count);

    }

    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int ans = 0;
        for (int x : nums) {
            if (!set.contains(x - 1)) {
                int y = x + 1;
                while (set.contains(y)) {
                    y++;
                }
                ans = Math.max(ans, y - x);
            }
        }

        return ans;
    }
}

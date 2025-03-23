package cn.xej.array;


import java.util.HashMap;
import java.util.Map;

/**
 * 题目描述
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 */
public class TowSum {
    ///方法一 冒泡排序
    //问题：你可以想出一个时间复杂度小于 O(n2) 的算法吗？

    /// 方法二 哈希表
    //我们可以使用一个哈希表d来存储每个元素及其对应的索引。 map key 存放值，value存放索引
    //遍历数组nums，对于当前元素nums[i]，我们首先判断target−nums[i]是否在哈希表d中，如
    // 果在d中，说明target值已经找到，返回target−nums[i]的索引和i即可。
    //时间复杂度O(n)，空间复杂度O(n)，其中n为数组nums的长度。
    public static void main(String[] args) {
        int[] nums = new int[] {0,1,0,3,12};
        twosum(nums, 4);
    }

    public static int[] twosum(int[] nums, int target) {
        Map<Integer, Integer> d = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            int y = target - x;
            if (d.containsKey(y)) {
                return new int[] {d.get(y), i};
            }
            d.put(x, i);
        }
        return new int[]{};
    }
}
package cn.xej.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目描述
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 *
 * 子数组是数组中元素的连续非空序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */
public class SubarraySum {


    /// 方法：哈希表 + 前缀和
    /// 定义个哈希表map存在前缀和，key为前缀和，value为前缀和出现的次数
    /// map初始化时需要将map.put(0,1)，假设【1，2】目标为3， 前缀和map是【1，3】，当3-3时 map【0】因为不存在 所以会漏算一次
    /// 核心逻辑是判断 map【前缀和-k】 是否存在，若存在就count++
    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1};
        int count = subarraySum(nums, 2);
        System.out.println(count);
    }

    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int s = 0;
        map.put(0,1);
        for (int i = 0; i < nums.length; i++) {
            s += nums[i];
            if (map.containsKey(s - k)) {
                //表示存在连续数组
                count++;
            }
            Integer num = map.getOrDefault(s, 0);
            map.put(s, num + 1);
        }
        return count;
    }
}

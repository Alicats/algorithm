package cn.xej.array;


/**
 * 题目描述
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 示例 2:
 *
 * 输入: nums = [0]
 * 输出: [0]
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 *
 */
public class MoveZeroes {

    ///方法 双指针
    //我们使用两个指针i和j,其中指针i指向当前已经处理好的序列的尾部，而指针j指向待处理序列的头部。初始时i=-1
    //遍历nums数组，如果nums[j]!=0,则将指针i指向的下一个数与nums[j]交换，同时i++,继续遍历直到j到达数组的尾部。
    //最后该数组的所有非零元素就按照原有顺序移到到数组头部，而所有零元素都被移动到数组的尾部
    public static void main(String[] args) {
        int[] nums = new int[] {0,1,0,3,12};
        moveZeroes(nums);
        System.out.printf("nums" + nums);
    }


    public static void moveZeroes(int[] nums) {
        int i = -1, n = nums.length;
        for (int j = 0; j < n; ++j) {
            if (nums[j] != 0) {
                int t = nums[++i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }


    }
}

package cn.xej.array;

/**
 * 题目描述
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 *
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：nums = [1], target = 0
 * 输出：-1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -104 <= target <= 104
 */
public class RotateBinarySearch {

    /// 方法 二分查找
    /// 使用二分法 将数组分割成[l,mid],[mid+1,r]两部分，我们可以发现 其中有一部分一定是有序的
    /// 因此我们可以根据有序的一部分，判断target是否在这一部分中：
    ///     若[l,mid]范围内的元素构成有序数组
    ///         若target满足这个范围，则缩小范围r=mid-1，不然target就在[mid+1,r]中寻找
    ///     若[mid+1,r]范围内的元素构成有序数组
    ///         若target满足这个范围，则缩小范围l=mid+1, 不然target就在[l,mid]中寻找
    ///
    ///
    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        System.out.println(search(nums, 0));
    }

    public static int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (target == nums[mid]) {
                return mid;
            }

            /// 若l-mid范围内的元素构成有序数组
            if (nums[l] <= nums[mid]) {
                /// 若target满足l-mid，则缩小范围r。否则在[mid,r]中寻找
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }else {
                ///  若mid-r范围内的元素构成有序数组
                ///  若target满足mid-r，则缩小范围l。否则在[l,mid]中寻找
                if (nums[mid] <= target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return l;
    }
}
package cn.xej.array;


public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = new int[] {-1,0,3,5,9,12};
        int index = binarySearch(nums, 2);
        System.out.printf("index "+ index);

    }

    public static int binarySearch(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] >= target) {
                r = mid;
            }else {
                l = mid + 1;
            }
        }
        return nums[l] == target ? l : -1;
    }
}

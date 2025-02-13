package com.BinarySearch;

public class challangeProblem {
        public static int findFirstMissingPositive(int[] nums) {
            int n = nums.length;

            // Step 1: Place each number at its correct index
            for (int i = 0; i < n; i++) {
                while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                    // Swap nums[i] with nums[nums[i] - 1]
                    int temp = nums[nums[i] - 1];
                    nums[nums[i] - 1] = nums[i];
                    nums[i] = temp;
                }
            }

            // Step 2: Find the first missing positive
            for (int i = 0; i < n; i++) {
                if (nums[i] != i + 1) {
                    return i + 1;
                }
            }

            return n + 1; // If all numbers are in place, return n + 1
        }
        public static int Binarysearchindex(int []array,int x) {
            int low = 0;
            int high = array.length - 1;
            while (low <= high) {
                int mid = low+(high-low)/2;
                if (array[mid] == x) {
                    return mid;
                } else if (array[mid] < x) {
                    low=mid+1;
                } else {
                    high = mid -1;
                }
            }
            return -1;
        }

        public static void main(String[] args) {
            int[] nums = {3, 4, -1, 1};
            System.out.println("First missing positive: " + findFirstMissingPositive(nums));
            int arr[]={1,2,4,5,6,7,8};
            int x=4;
            System.out.println("The index of the target value is "+Binarysearchindex(arr,x));
        }


}

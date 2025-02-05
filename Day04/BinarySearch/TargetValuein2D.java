package com.BinarySearch;

public class TargetValuein2D {
    public static boolean binarysearch(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == x) {
                return true;
            } else if (arr[mid] < x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    static boolean searchMatrix(int[][] mat, int x) {
        int n = mat.length;
        int m = mat[0].length;
        int low = 0;
        int high = n - 1;
        int row = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (x == mat[mid][0])
                return true;
            if (x > mat[mid][0]) {
                row = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }

        }
        if (row == -1)
            return false;
        return binarysearch(mat[row], x);
    }

    public static void main(String[] args) {
        int[][] array = {{1, 5, 9}, {14, 20, 21}, {30, 34, 43}};
        int targetvalue = 5;
        boolean result = searchMatrix(array, targetvalue);
        if (result) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }

    }
}

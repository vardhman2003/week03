package com.BinarySearch;

public class peakElement {
    public static int peakValue(int arr[], int n) {
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return arr[mid];
            } else if (arr[mid] < arr[mid - 1]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;

    }

    public static void main(String[] args) {
      int arr[]={1,2,4,5,6,7,8,5,1};
      int n=arr.length;
      int finalResult=peakValue(arr,n);
        System.out.println("The peak element for the array is "+finalResult);
    }
}

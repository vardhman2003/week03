package com.BinarySearch;

public class rotationPoint {
    public static int rotationPointofArray(int arr[],int n) {
        int start = 0;
        int end = n - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] > end) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }


    public static void main(String[] args) {
        int arr[]={1,2,4,5,6,7,9};
        int n=arr.length;
        int result=rotationPointofArray(arr,n);
        System.out.println("The point from where the array is rotated is "+result);



    }
}

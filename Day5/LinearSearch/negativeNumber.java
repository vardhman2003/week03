package com.LinearSearch;

public class negativeNumber {
    public static int firstNegativeNumber(int[]arr,int n){
        int index=-1;
        boolean flag=false;
        for(int i=0;i<arr.length;i++){
            if(arr[i]<0){
                index=i;
                flag=true;
                break;
            }
        }
        if(flag){
            return index;
        }
        return -1;


    }

    public static void main(String[] args) {
        int arr[]={1,2,1,5,6};
        int n=arr.length;
        int finalIndex=firstNegativeNumber(arr,n);
        System.out.println("The resultent index value is "+finalIndex);


    }
}

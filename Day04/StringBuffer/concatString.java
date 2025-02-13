package com.StringBuffer;

import java.util.Scanner;

public class concatString {
    public static String concatString(String[]str){
        StringBuffer sb=new StringBuffer();
        for(String sr:str){
            sb.append(sr);
        }
        return sb.toString();

    }
    public static void main(String[] args) {
        String []words={"Nishant","Kumar","Bhagat"};
        String result=concatString(words);
        System.out.println("The resultent String value is "+result);


        }
    }
/*
You are given an array of strings. Write a program that uses StringBuffer to concatenate all the strings in the array efficiently.
Approach:
Create a new StringBuffer object.
Iterate through each string in the array and append it to the StringBuffer.
Return the concatenated string after the loop finishes.
Using StringBuffer ensures efficient string concatenation due to its mutable nature.

 */

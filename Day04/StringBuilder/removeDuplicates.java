package com.StringBuilder;

import java.util.HashSet;

public class removeDuplicates {
    public static void main(String[] args) {
        StringBuilder str=new StringBuilder("aaaabbbb");
        HashSet<String>hs=new HashSet<>();
        for(int i=0;i<str.length();i++){
            hs.add(String.valueOf(str.charAt(i)));
        }
        StringBuilder duplicateString=new StringBuilder();
        for(String ch:hs){
            duplicateString.append(ch);

        }
        System.out.println("The String Without Duplicate value is "+duplicateString);
    }
}

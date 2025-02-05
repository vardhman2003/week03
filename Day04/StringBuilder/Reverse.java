package com.StringBuilder;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Reverse {
    private static StringBuilder reverse(String str){
        return new StringBuilder(str).reverse();
    }
     public static void main(String[] args) throws IOException {
         BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         String str=br.readLine();
         System.out.println("The reverse value of the String is "+reverse(str));



     }
}

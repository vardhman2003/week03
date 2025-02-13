package com.fileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class readFileusingFileReader {
    public static void main(String[] args) throws IOException {
        // FileReader fileReader=new FileReader("BridgeLabz.txt");
        String filePath = "/fileReader/BridgeLabz.txt";
        System.out.println("Reading the Char line by line");
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("The error occured in the file is " + e.getMessage());
        }
    }
}
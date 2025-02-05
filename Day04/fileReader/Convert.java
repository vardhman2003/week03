package com.fileReader;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Convert {

        public static void main(String[] args) {
            // check length
            if (args.length != 1) {
                System.out.println("Usage: java ByteToCharacterStream <file-path>");
                return;
            }
            // taking file using console
            String filePath = args[0];
            // try block for implement logic
            try {
                FileInputStream fileInputStream = new FileInputStream(filePath);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line;
                // print all the words that contains in given file
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
                // close all
                bufferedReader.close();
                inputStreamReader.close();
                fileInputStream.close();

                // catch block for exception
            } catch (IOException e) {
                System.err.println("An error occurred while reading the file: " + e.getMessage());
            }
        }
    }

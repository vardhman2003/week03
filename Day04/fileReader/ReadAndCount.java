package com.fileReader;
import java.io.*;

class CountAndFind {
    public static void main(String[] args) {
        // check length
        if (args.length != 2) {
            System.out.println("Usage: java WordCounter <file-path> <word-to-count>");
            return;
        }
        // take user input and file by console
        String filePath = args[0];
        String wordToCount = args[1];
        int count = 0;
        // try block for write a execution part
        try {
            FileReader fileReader = new FileReader("myfile.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            // read and print line by line words
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\W+");
                for (String word : words) {
                    if (word.equalsIgnoreCase(wordToCount)) {
                        count++;
                    }
                }
            }
            // close all
            bufferedReader.close();
            System.out.println("The word '" + wordToCount + "' appears " + count + " times.");
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}

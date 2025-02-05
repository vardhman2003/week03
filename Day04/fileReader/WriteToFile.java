package com.fileReader;
import java.io.FileWriter;
import java.io.IOException;

    public class WriteToFile {
        public static void main(String[] args) throws IOException {
            try {
                FileWriter myWriter = new FileWriter("myfile.txt");
                for(int i=0; i<3000; i++) {
                    myWriter.write("Files in Java might be tricky, but it is fun enough!");
                }
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

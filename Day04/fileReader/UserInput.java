package com.fileReader;

import java.io.*;

public class UserInput{

        public static void main(String[] args) {
            String fPath = "mycostumefile.txt"; // File path to store the user input
            try {
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                FileWriter fw = new FileWriter(fPath, true); // 'true' to append data
                BufferedWriter bw = new BufferedWriter(fw);

                System.out.println("Enter text (type 'exit' to stop):");

                String input;

                // Continuously read input from the console until 'exit' is entered
                while (true) {
                    // Read user input
                    input = br.readLine();

                    // Check if the input is 'exit', and break the loop if true
                    if ("exit".equalsIgnoreCase(input)) {
                        break;
                    }

                    // Write the input to the file
                    bw.write(input);
                    bw.newLine();  // Adds a newline to separate each input
                }

                // Close the file after input is finished
                bw.close();
                br.close();

                System.out.println("Input written to file: " + fPath);

            } catch (IOException e) {
                // Handle IOExceptions (e.g., file not found, access issues)
                System.err.println("An error occurred: " + e.getMessage());
            }
        }
    }

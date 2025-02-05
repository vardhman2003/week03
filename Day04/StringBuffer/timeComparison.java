package com.StringBuffer;

public class timeComparison {
    public static void main(String[] args) {


        long startTime, endTime;
        StringBuilder stringBuilder = new StringBuilder("BridgeLabz");
        startTime = System.nanoTime();
        for (int i = 0; i < 10000000; i++) {
            stringBuilder.append("Mentors");
        }
        endTime = System.nanoTime();
        System.out.println("The time takend by StringBuilder is " + (endTime - startTime));
        StringBuffer stringBuffer = new StringBuffer("BridgeLabz");
        startTime = System.nanoTime();
        for (int i = 0; i < 100000000; i++) {
            stringBuffer.append("Mentors");
        }
        endTime = System.nanoTime();
        System.out.println("The time taken by StringBuffer is " + (endTime - startTime));


    }
}

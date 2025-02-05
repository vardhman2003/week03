package com.LinearSearch;


public class findingWord {
    public static String findSentence(String[]Sentences,String Word){
        for (String sentence:Sentences){
            if(sentence.contains(Word)){
                return sentence;
            }
        }
        return "Word Not Found";
    }

    public static void main(String[] args) {
        String []Sentences={"BridgeLabz is a Java Institute","Technocrats Group Bhopal"};
        String specificWord="BridgeLabz";
        String result=findSentence(Sentences,specificWord);
        System.out.println(result);


    }

}
/*
You are given an array of sentences (strings). Write a program that performs Linear Search to find the first sentence containing a specific word. If the word is found, return the sentence. If no sentence contains the word, return "Not Found".
Approach:
Iterate through the list of sentences.
For each sentence, check if it contains the specific word.
If the word is found, return the current sentence.
If no sentence contains the word, return "Not Found".

 */

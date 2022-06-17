package com.example.dictionary;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Words extends FileOperations {

    private int wordCounter;
    ArrayList<String> listWithWords = new ArrayList<String>();

    public int getWordCounter() {
        return wordCounter;
    }

    // reading from a file and creating a list with data
    public void createList() {

        wordCounter = 0; // how many words are in the txt file
        System.out.println(getFileName());

        try {
            java.io.File plik = new java.io.File(getFileName()); // reading the file
            Scanner in = new Scanner(plik);
            System.out.println("Wczytano plik");
            while (in.hasNextLine()) {
                wordCounter++; // word counting
                String oneWord = in.nextLine();
                listWithWords.add(oneWord); // adding to the list
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku.");
            e.printStackTrace();
        }
        System.out.println("W pliku znajduje sie: " + wordCounter + " slowek.");
    }


}

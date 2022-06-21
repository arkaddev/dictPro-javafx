package com.example.dictionary;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Words extends FileOperations {

    private int wordCounter;

    private static String plMeaning;
    private static String engMeaning;

    ArrayList<String> listWithWords = new ArrayList<String>();

    public int getWordCounter() {
        return wordCounter;
    }

    public static String getPlMeaning() {
        return plMeaning;
    }

    public static String getEngMeaning() {
        return engMeaning;
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


// drawing a phrase from the list and splitting the expression into 2 separate strings

    public String drawWord() {

        Random random = new Random(); // generates random integer
        int drawnNumber = random.nextInt(getWordCounter()); // draw a number from 0 to
        String phraze = listWithWords.get(drawnNumber);

        // there are words in the txt file that do not have a translation
        // if there is - sign , it means that the word has a translation

        if (phraze.contains("-")) {
            engMeaning = phraze.substring(0, phraze.indexOf("-") - 1);
            plMeaning = phraze.substring(phraze.indexOf("-") + 2, phraze.length());
           //engMeaningId.setText(engMeaning);

            if (plMeaning.contains(",")) {
                System.out.println("Zawiera przecinek");
                plMeaning = phraze.substring(phraze.indexOf("-") + 2, phraze.indexOf(","));
            }

            // hint for the user, display the number of characters, by sign -

            // * char[] polMeaningTab; polMeaningTab = polMeaning.toCharArray();
            // *
            // * for (int i = 0; i < polMeaning.length(); i++) {
            // *
            // * if (Character.isLetter(polMeaningTab[i])) { System.out.print("-"); } else {
            // * System.out.print(" "); } } System.out.println();

            System.out.println("Wylosowano slowo: " + engMeaning);

        }
        return engMeaning;
    }





}

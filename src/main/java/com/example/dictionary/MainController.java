package com.example.dictionary;

import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MainController {

    private String fileName;

    @FXML
    private Label fileInfoId;
    @FXML
    private MenuItem startId;
    @FXML
    private Label engMeaningId;
    @FXML
    private TextArea plMeaningId;
    @FXML
    private Label respondInfoId;
    @FXML
    private Label whichWordId;
    @FXML
    private Label percentageId;
    @FXML
    private Button buttonOkId;
    @FXML
    private MenuItem importFileId;


    // method sets label to null
    @FXML
    void initialize() {

        engMeaningId.setText("Wczytaj plik");

        respondInfoId.setText(null);
        whichWordId.setText(null);
        percentageId.setText(null);
        buttonOkId.setDisable(true);
        plMeaningId.setDisable(true);

    }


    // creating a file load window
    @FXML
    void importFileOnAction() {

        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Otworz plik");
        File f = fileChooser.showOpenDialog(null);
        fileName = f.getName();


        if (fileChooser != null) {
            startId.setDisable(false);
        } else {
            System.out.println("Nie wczytano pliku");
        }
        createList();
        fileInfoId.setText("Wczytano plik " + fileName + ". Ilosc wyrazow: " + wordCounter);
        engMeaningId.setText("Nacisnij Start");
    }

    int wordCounter;
    ArrayList<String> listWithWords = new ArrayList<String>();

    // reading from a file and creating a list with data
    public void createList() {

        wordCounter = 0; // how many words are in the txt file

        try {
            File plik = new File(fileName); // reading the file
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

    String plMeaning;
    String engMeaning;

    // drawing a phrase from the list and splitting the expression into 2 separate strings
    public void drawWord() {

        Random random = new Random(); // generates random integer
        int drawnNumber = random.nextInt(wordCounter); // draw a number from 0 to
        String phraze = listWithWords.get(drawnNumber);

        // there are words in the txt file that do not have a translation
        // if there is - sign , it means that the word has a translation

        if (phraze.contains("-")) {
            engMeaning = phraze.substring(0, phraze.indexOf("-") - 1);
            plMeaning = phraze.substring(phraze.indexOf("-") + 2, phraze.length());
            engMeaningId.setText(engMeaning);

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
    }

    // operations related to the use of the start option
    @FXML
    void startOnAction() {
        drawWord();
        buttonOkId.setDisable(false);
        plMeaningId.setDisable(false);

        startId.setDisable(true);
        importFileId.setDisable(true);

    }

    int goodAnswersCounter;
    int badAnswersCounter;


    float percentage;

    // operations related to the use of the start option
    // displays information about the answer and counts the percentage of good results
    @FXML
    void buttonOkOnAction() {

        String myrespond = plMeaningId.getText();

        if (myrespond.equals(plMeaning)) {
            respondInfoId.setText("Odpowiedz poprawna");
            respondInfoId.setStyle("-fx-background-color: #008000;");

            goodAnswersCounter++;
            percentage = (goodAnswersCounter * 100) / (goodAnswersCounter + badAnswersCounter);
            String p = Float.toString(percentage);
            percentageId.setText(p + "%");


        } else {
            respondInfoId.setText("Odpowiedz bledna");
            respondInfoId.setStyle("-fx-background-color: #ff0000;");

            badAnswersCounter++;
            percentage = (goodAnswersCounter * 100) / (goodAnswersCounter + badAnswersCounter);
            String p = Float.toString(percentage);
            percentageId.setText(p + "%");

        }
        drawWord();
        plMeaningId.setText("");

        String sumOfWords = Integer.toString(goodAnswersCounter + badAnswersCounter);
        whichWordId.setText(sumOfWords);
    }

    // deletion of information about the response result
    @FXML
    void plMeaningOnMouseClicked() {

        respondInfoId.setText("");
        respondInfoId.setStyle(null);
    }


    // dialogue window about
    @FXML
    void aboutOnAction() {
        Dialog.dialogAboutApplication();
    }

}


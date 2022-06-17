package com.example.dictionary;

import javafx.fxml.FXML;

import javafx.scene.control.*;

import java.util.Random;

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

        startId.setDisable(true);
        respondInfoId.setText(null);
        whichWordId.setText(null);
        percentageId.setText(null);
        buttonOkId.setDisable(true);
        plMeaningId.setDisable(true);

    }

    FileOperations fileOperations = new FileOperations();
    Words words = new Words();

    // creating a file load window
    @FXML
    void importFileOnAction() {

        fileOperations.chooseFile();
        System.out.println(FileOperations.getFileName());
        words.createList();
        startId.setDisable(false);
        fileInfoId.setText("Wczytano plik " + fileName + ". Ilosc wyrazow: " + words.getWordCounter());
        engMeaningId.setText("Nacisnij Start");

    }


    String plMeaning;
    String engMeaning;

    // drawing a phrase from the list and splitting the expression into 2 separate strings
    public void drawWord() {

        Random random = new Random(); // generates random integer
        int drawnNumber = random.nextInt(words.getWordCounter()); // draw a number from 0 to
        String phraze = words.listWithWords.get(drawnNumber);

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
        DialogWindow.dialogAboutApplication();
    }

}


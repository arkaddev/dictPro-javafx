package com.example.dictionary;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class MainController {

    @FXML
    private Label fileInfoId;
    @FXML
    private MenuItem testId;
    @FXML
    private MenuItem startLearningId;
    @FXML
    private MenuItem endLearningId;
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

    private boolean testInfo;

    FileOperations fileOperations = new FileOperations();
    Words words = new Words();


    // method sets label to null
    @FXML
    void initialize() {

        engMeaningId.setText("Wczytaj plik");

        testId.setDisable(true);
        startLearningId.setDisable(true);
        endLearningId.setDisable(true);
        respondInfoId.setText(null);
        whichWordId.setText(null);
        percentageId.setText(null);
        buttonOkId.setDisable(true);
        plMeaningId.setDisable(true);

    }

    // creating a file load window
    @FXML
    void importFileOnAction() {

        fileOperations.chooseFile();
        //System.out.println(FileOperations.getFileName());
        words.createList();
        startLearningId.setDisable(false);
        testId.setDisable(false);
        fileInfoId.setText("Wczytano plik " + FileOperations.getFileName() + ". Ilosc wyrazow: " + words.getWordCounter());
        engMeaningId.setText("Nacisnij Start");

    }

    @FXML
    void testOnAction() {
        testInfo = true;

        engMeaningId.setText(words.drawWord());
        buttonOkId.setDisable(false);
        plMeaningId.setDisable(false);

        testId.setDisable(true);
        startLearningId.setDisable(true);
        importFileId.setDisable(true);


    }

    // operations related to the use of the start option
    @FXML
    void startLearningOnAction() {
        testInfo = false;

        //words.drawWord();
        //engMeaningId.setText(Words.getEngMeaning());
        engMeaningId.setText(words.drawWord());
        buttonOkId.setDisable(false);
        plMeaningId.setDisable(false);

        testId.setDisable(true);
        startLearningId.setDisable(true);
        importFileId.setDisable(true);
        endLearningId.setDisable(false);

    }

    // dialogue window about learning
    @FXML
    void endLearningOnAction() {

        DialogWindow.dialogLearning();
        endLearningId.setDisable(true);
        importFileId.setDisable(false);
        buttonOkId.setDisable(true);
        plMeaningId.setDisable(true);


    }


    int goodAnswersCounter;
    int badAnswersCounter;


    float percentage;

    // operations related to the use of the start option
    // displays information about the answer and counts the percentage of good results
    @FXML
    void buttonOkOnAction() {


        String myrespond = plMeaningId.getText();

        if (myrespond.equals(Words.getPlMeaning())) {
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

        if (testInfo && (goodAnswersCounter + badAnswersCounter == 5)) {

            System.out.println("Test zakonczony");
            DialogWindow.dialogTest();

            endLearningId.setDisable(true);
            importFileId.setDisable(false);
            buttonOkId.setDisable(true);
            plMeaningId.setDisable(true);

        }

        if (!(testInfo && (goodAnswersCounter + badAnswersCounter == 5))) {
            //words.drawWord();
            //engMeaningId.setText(Words.getEngMeaning());
            engMeaningId.setText(words.drawWord());

        }
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

    // dialogue window about statistics
    @FXML
    void statisticsOnAction() throws IOException {
words.f();
    }
}


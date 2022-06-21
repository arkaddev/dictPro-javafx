package com.example.dictionary;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileOperations {

    private static String fileName;

    public static String getFileName() {

        return fileName;
    }

    // creating a file load window
    public void chooseFile() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Otworz plik");
        java.io.File f = fileChooser.showOpenDialog(null);
        fileName = f.getName();
    }








}

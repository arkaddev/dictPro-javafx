package com.example.dictionary;

import javafx.scene.control.Alert;

public class DialogWindow {

    public static void dialogAboutApplication(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("O programie");
        alert.setHeaderText("DictPro 1.0");
        alert.setContentText("Wersja testowa aplikacji do nauki jezyka angielskiego.");

        alert.showAndWait();

    }

}

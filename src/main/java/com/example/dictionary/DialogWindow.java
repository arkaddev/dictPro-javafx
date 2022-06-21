package com.example.dictionary;

import javafx.scene.control.Alert;

public class DialogWindow {

    public static void dialogAboutApplication() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("O programie");
        alert.setHeaderText("DictPro 1.0");
        alert.setContentText("Wersja testowa aplikacji do nauki jezyka angielskiego.");

        alert.showAndWait();

    }
    public static void dialogLearning() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Podsumowanie");
        alert.setHeaderText("Wynik: " + MainController.percentage2 + "%");
        alert.setContentText("WLiczba poprawnych odpowidzi: \n Liczba blednych odpowiedzi:");

        alert.showAndWait();

    }
    public static void dialogTest() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Podsumowanie Testu");
        alert.setHeaderText("Wynik: " + MainController.percentage2 + "%");
        alert.setContentText("WLiczba poprawnych odpowidzi: \n Liczba blednych odpowiedzi:");

        alert.showAndWait();

    }


}

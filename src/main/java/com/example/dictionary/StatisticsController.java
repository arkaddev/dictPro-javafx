package com.example.dictionary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class StatisticsController {


    @FXML
    private LineChart<?, ?> chartId;


    @FXML
    void initialize() {
        createChart();
    }

    public void openStatistics() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("StatisticsWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Statystyki");
        stage.setScene(scene);
        stage.show();

    }


    public void createChart() {

        readStatisticsFile();

        chartId.setTitle("Wyniki nauki");
        chartId.getXAxis().setLabel("data");
        chartId.getYAxis().setLabel("wynik");

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("user3");


        for (int i = 0; i < statisticsList.size(); i++) {

            String s = statisticsList.get(i);
            String p = s.substring(0, s.indexOf("."));
            String d = s.substring(s.indexOf(" ") + 1, s.length());

            int pI = Integer.parseInt(p);

            series3.getData().add(new XYChart.Data(d, pI));
        }

        chartId.getData().add(series3);
    }


    ArrayList<String> statisticsList = new ArrayList<String>();

    public void readStatisticsFile() {
        try {
            File resultsFile = new File("statistics.txt");
            Scanner readResults = new Scanner(resultsFile);
            System.out.println("Wczytano plik.");
            while (readResults.hasNextLine()) {
                String userData = readResults.nextLine();
                statisticsList.add(userData);

            }
            readResults.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku.");
            e.printStackTrace();
        }
    }


    public void saveStatisticsFile() {

        Calendar calendar = Calendar.getInstance();

        try {
            FileWriter saveFile = new FileWriter("statistics.txt");
            for (int i = 0; i < statisticsList.size(); i++) {
                String name = statisticsList.get(i);
                saveFile.write(name + "\n");
            }

            saveFile.write(MainController.percentage2 + " ");
            saveFile.write(calendar.get(Calendar.DAY_OF_MONTH) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR));


            saveFile.close();
            System.out.println("Wynik zostal zapisany");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void saveStatistics() {

        readStatisticsFile();
        saveStatisticsFile();
    }


}

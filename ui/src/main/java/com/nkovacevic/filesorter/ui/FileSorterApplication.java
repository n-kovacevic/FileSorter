package com.nkovacevic.filesorter.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class FileSorterApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL fxmlFile = getClass().getClassLoader().getResource("main.fxml");
        if (fxmlFile != null) {
            Pane root = FXMLLoader.load(fxmlFile);
            Scene scene = new Scene(root);
            primaryStage.setTitle("File Sorter");
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
            Alert alert = new Alert(AlertType.ERROR, "Layout file could not be loaded.", ButtonType.CLOSE);
            alert.showAndWait();
        }
    }
}

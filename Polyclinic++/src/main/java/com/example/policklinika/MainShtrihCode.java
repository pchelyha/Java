package com.example.policklinika;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainShtrihCode extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("shtrih_code.fxml"));
        primaryStage.setTitle("Polyclinic++");
        primaryStage.getIcons().add(new Image("12_Patrons.png"));
        primaryStage.setScene(new Scene(root, 200, 200));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

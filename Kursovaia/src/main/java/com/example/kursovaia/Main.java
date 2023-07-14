package com.example.kursovaia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sample.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 250, 400);
        stage.setTitle("Больница");
        stage.getIcons().add(new Image("12345.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)  {
        launch();

    }
}
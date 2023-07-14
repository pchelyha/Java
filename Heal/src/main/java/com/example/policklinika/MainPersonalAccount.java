package com.example.policklinika;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPersonalAccount extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("personal_account.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 395.0, 414.0);
        stage.setTitle("Polyclinic++");
        stage.getIcons().add(new Image("12_Patrons.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)  {
        launch();

    }
}

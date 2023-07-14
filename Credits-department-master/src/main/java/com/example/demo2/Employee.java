package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Employee implements Initializable {

    @FXML
    private ImageView fotoUser;
    @FXML
    private Label welcome;
    @FXML
    public Label position;
    @FXML
    public Button exit;
    @FXML
    public Button loan;

    public static String firstName = " ";
    public static String dolgnost = " ";
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {

//        File fi=new File("src/img/"+firstName+".jpeg");
        File fi=new File("target/classes/img/"+firstName.split(" ")[0]+".png");
        Image image= new Image(fi.toURI().toString());
        fotoUser.setImage(image);
        welcome.setText("Добро пожаловать, "+firstName);
        position.setText("Ваша должность: "+dolgnost);


    }

    public void Client(){
        Client.dolgnost=dolgnost;
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Client.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        primaryStage.setTitle("Оформление кредита");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setResizable(false);
        primaryStage.show();
        Stage stage = (Stage) loan.getScene().getWindow();
        stage.close();
    }
    public void LoanProcessing(ActionEvent actionEvent) {
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Order.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        primaryStage.setTitle("Оформление кредита");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setResizable(false);
        primaryStage.show();
        Stage stage = (Stage) loan.getScene().getWindow();
        stage.close();
    }
    public void exitAccaunt(ActionEvent actionEvent) {
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        primaryStage.setTitle("Авторизация");
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 276, 400));
        primaryStage.show();
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
}
package com.example.demo2;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class admin  implements Initializable {
    @FXML
    private ImageView fotoUser1;
    @FXML
    private Label welcome;
    @FXML
    public Label position;
    @FXML
    public Button hystoryVhod;
    @FXML
    public Button exit;
    @FXML
    private TextField daaOthcet;
    @FXML
    private Button save;

    public static String firstName = " ";
    public static String dolgnost = " ";


    DB db = null;
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {

        File fi=new File("target/classes/img/"+firstName.split(" ")[0]+".png");

        Image image= new Image(fi.toURI().toString());
        fotoUser1.setImage(image);
        welcome.setText("Добро пожаловать, "+firstName);
        position.setText("Ваша должность: "+dolgnost);

    }

public void employee(){
    Stage primaryStage = new Stage();
    Parent root = null;
    try {
        root = FXMLLoader.load(getClass().getResource("addEmployee.fxml"));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    primaryStage.setTitle("Добавление сотрудника");
    primaryStage.getIcons().add(new Image("icon.png"));
    primaryStage.setResizable(false);
    primaryStage.setScene(new Scene(root, 600, 400));
    primaryStage.show();
    Stage stage = (Stage) exit.getScene().getWindow();
    stage.close();
}
    public void loadingLoans(ActionEvent actionEvent)  {
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("current_loans.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        primaryStage.setTitle("Кредиты");
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 900, 400));
        primaryStage.show();
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
    public void loadingHistory(ActionEvent actionEvent)  {
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("history.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        primaryStage.setTitle("История входа");
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        Stage stage = (Stage) exit.getScene().getWindow();
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

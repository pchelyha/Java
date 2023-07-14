package com.example.demo2;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Client implements Initializable {
    @FXML
    private TextField idClient;
    @FXML
    private javafx.scene.control.CheckBox check;
    @FXML
    private TextField FIOClient;
    @FXML
    private TextField pass1;
    @FXML
    public javafx.scene.control.Label empty1;
    @FXML
    private TextField PasportData;
    @FXML
    private TextField mail;
    @FXML
    private PasswordField password;
    @FXML
    public javafx.scene.control.Button back1;
    @FXML
    private TextField tel;
    @FXML
    private javafx.scene.control.Button accept1;
    @FXML
    private TextField address;

    DB db = new DB();
    public static String dolgnost = " ";
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        String number;
        try {
            number = db.getNumberMaxClient().toString();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        idClient.setPromptText(number);

    }
    public void click() {

        if (check.isSelected()) {
            pass1.setText(password.getText());
            password.setVisible(false);
            pass1.setVisible(true);
        } else {
            password.setText(pass1.getText());
            pass1.setVisible(false);
            password.setVisible(true);
        }
    }
    public void Loan(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if ((idClient.getText().equals("") || FIOClient.getText().equals("") || PasportData.getText().equals("")
                || tel.getText().toString().equals("") || address.getText().toString().equals("") || password.getText().toString().equals("") ||
                mail.getText().toString().equals("") || check.isSelected()) && pass1.getText().toString().equals("")) {
            PauseTransition hide = new PauseTransition(Duration.seconds(0));
            PauseTransition hide1 = new PauseTransition(Duration.seconds(0));
            PauseTransition hide2 = new PauseTransition(Duration.seconds(3));
            PauseTransition hide3 = new PauseTransition(Duration.seconds(3));
            hide.setOnFinished(e -> accept1.setVisible(false));
            hide1.setOnFinished(e -> empty1.setVisible(true));
            hide.play();
            hide1.play();
            hide2.setOnFinished(e -> accept1.setVisible(true));
            hide3.setOnFinished(e -> empty1.setVisible(false));
            hide2.play();
            hide3.play();

        } else {
            if(check.isSelected()){
                db.Insert_Client(idClient.getText(),FIOClient.getText(),PasportData.getText(),tel.getText(),address.getText(),
                        mail.getText(),pass1.getText());
            } else {
                db.Insert_Client(idClient.getText(),FIOClient.getText(),PasportData.getText(),tel.getText(),address.getText(),
                        mail.getText(),password.getText());
            }
        }
    }

    public void BackToProfile(ActionEvent actionEvent) {
        if (Objects.equals(dolgnost, "Кредитный специалист")) {
            Stage primaryStage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("Employee1.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            primaryStage.setTitle("Профиль");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.getIcons().add(new Image("icon.png"));
            primaryStage.setResizable(false);
            primaryStage.show();
            Stage stage = (Stage) back1.getScene().getWindow();
            stage.close();
        }else{
            Stage primaryStage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("Employee.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            primaryStage.setTitle("Профиль");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.getIcons().add(new Image("icon.png"));
            primaryStage.setResizable(false);
            primaryStage.show();
            Stage stage = (Stage) back1.getScene().getWindow();
            stage.close();
        }
    }

}

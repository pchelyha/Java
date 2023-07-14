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


public class AddEmployee implements Initializable {
    @FXML
    private TextField numberZacaz;
    @FXML
    private javafx.scene.control.CheckBox check;
    @FXML
    private TextField log;
    @FXML
    private TextField pass1;
    @FXML
    private TextField fio;
    @FXML
    private TextField pas;
    @FXML
    public javafx.scene.control.Label empty;


    @FXML
    public javafx.scene.control.Button back;
    @FXML
    private ComboBox post;
    @FXML
    private javafx.scene.control.Button accept;


    DB db = new DB();
    private static final int CANVAS_SIZE = 150;

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {

        String number;
        try {
            number = db.getNumberMaxEmployee().toString();
            ArrayList<String> Post = db.Post();

            for (int x =0; x<Post.size();x++) {
                post.getItems().add(Post.get(x));
            }
            post.setValue("Должность не выбрана");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        numberZacaz.setPromptText(number);
        numberZacaz.setFocusTraversable(false);
    }
    public void click() {

        if (check.isSelected()) {
            pass1.setText(pas.getText());
            pas.setVisible(false);
            pass1.setVisible(true);
        } else {
            pas.setText(pass1.getText());
            pass1.setVisible(false);
            pas.setVisible(true);
        }
    }
    public void Loan(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (numberZacaz.getText().equals("") || log.getText().equals("") || fio.getText().equals("")|| pas.getText().toString().equals("")
                || post.getValue().toString().equals("Должность не выбрана")|| check.isSelected() && pass1.getText().toString().equals(""))
         {
            PauseTransition hide = new PauseTransition(Duration.seconds(0));
            PauseTransition hide1 = new PauseTransition(Duration.seconds(0));
            PauseTransition hide2 = new PauseTransition(Duration.seconds(3));
            PauseTransition hide3 = new PauseTransition(Duration.seconds(3));
            hide.setOnFinished(e -> accept.setVisible(false));
            hide1.setOnFinished(e -> empty.setVisible(true));
            hide.play();
            hide1.play();
            hide2.setOnFinished(e -> accept.setVisible(true));
            hide3.setOnFinished(e -> empty.setVisible(false));
            hide2.play();
            hide3.play();

        } else {
            if(check.isSelected()){
                db.Insert_Employee(numberZacaz.getText(),post.getValue().toString(),fio.getText(),log.getText(),pass1.getText());
            } else {
                db.Insert_Employee(numberZacaz.getText(),post.getValue().toString(),fio.getText(),log.getText(),pas.getText());
            }
        }
    }

    public void BackToProfile(ActionEvent actionEvent) {
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        primaryStage.setTitle("Профиль");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.setResizable(false);
        primaryStage.show();
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }



}

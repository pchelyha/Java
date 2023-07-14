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


public class Order implements Initializable {
    @FXML
    private TextField numberZacaz;

    @FXML
    private TextField date_take;
    @FXML
    public javafx.scene.control.Label empty;
    @FXML
    private TextField date_return;
    @FXML
    private TextField timeProcat;
    @FXML
    private Canvas canvas;
    @FXML
    private Label er;
    private Window primaryStage;
    @FXML
    public javafx.scene.control.Button back;
    @FXML
    private ComboBox client;
    @FXML
    private javafx.scene.control.Button accept;
    @FXML
    private ComboBox loan;

    DB db = new DB();
    private static final int CANVAS_SIZE = 150;

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {

        String number;
        ArrayList<String> FIO = null;
        ArrayList<String> NameLoans = null;
        try {
            number = db.getNumberMaxZacaz().toString();
            FIO = db.FIO_client();
            NameLoans = db.NameLoans();
            for (int x = 0; x < FIO.size(); ++x) {
                client.getItems().add(FIO.get(x) + " - " + FIO.get(++x));
            }
            for (int x = 0; x < NameLoans.size(); ++x) {
                loan.getItems().add(NameLoans.get(x) + " - " + NameLoans.get(++x));
            }
            client.setValue("Клиент не выбран");
            loan.setValue("Вид кредита не выбран");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        numberZacaz.setPromptText(number);
        numberZacaz.setFocusTraversable(false);
        date_take.setPromptText("ГГГГ-ММ-ДД ЧЧ:ММ:СС");
        date_return.setPromptText("ГГГГ-ММ-ДД ЧЧ:ММ:СС");
    }

    public void Loan(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (numberZacaz.getText().equals("") || date_take.getText().equals("") || date_return.getText().equals("")
                || client.getValue().toString().equals("Клиент не выбран") || loan.getValue().toString().equals("Вид кредита не выбран")) {
            System.out.print(client.getValue().toString());
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
             db.Insert_Loan(numberZacaz.getText().toString(),client.getValue().toString().split(" ")[0],
                     "2", loan.getValue().toString().split(" ")[0],date_take.getText().toString(),
                     date_return.getText().toString(),"0");
        }
    }

    public void BackToProfile(ActionEvent actionEvent) {
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
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }

}

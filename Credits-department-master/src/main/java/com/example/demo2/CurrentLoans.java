package com.example.demo2;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CurrentLoans {

    private ObservableList<loansData> report = FXCollections.observableArrayList();

    DB db = new DB();

    @FXML
    private TableView<loansData> tableView;
    @FXML
    public javafx.scene.control.Button back1;
    @FXML
    private TableColumn<loansData, String> tableColumn1;

    @FXML
    private TableColumn<loansData, String> tableColumn2;
    @FXML
    private TableColumn<loansData, String> tableColumn3;

    @FXML
    private TableColumn<loansData, String> tableColumn4;
    @FXML
    private TableColumn<loansData, String> tableColumn5;

    @FXML
    private TableColumn<loansData, String> tableColumn6;

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        ArrayList<String> name = null;
        try {
            name = db.getOtchet();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(int i = 0; i < name.size(); ++i){
            report.add(new loansData(name.get(i), name.get(++i), name.get(++i), name.get(++i), name.get(++i), name.get(++i)));
        }

//Задаем тип данных переменным
        tableColumn1.setCellValueFactory(new PropertyValueFactory<loansData, String>("idloans"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<loansData, String>("client"));
        tableColumn3.setCellValueFactory(new PropertyValueFactory<loansData, String>("empl"));
        tableColumn4.setCellValueFactory(new PropertyValueFactory<loansData, String>("loan"));
        tableColumn5.setCellValueFactory(new PropertyValueFactory<loansData, String>("date_b"));
        tableColumn6.setCellValueFactory(new PropertyValueFactory<loansData, String>("date_e"));
        tableView.setItems(report);
    }
    public void savaOtchet(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, FileNotFoundException, DocumentException {
        db = new DB();
        Font f1 = FontFactory.getFont("DejaVuSans.ttf", "cp1251", BaseFont.EMBEDDED, 10);
        ArrayList<String > name = db.getOtchet();
        Document document1 = new Document();
        PdfWriter.getInstance(document1, new FileOutputStream("file.pdf"));
        document1.open();
        for(int i = 0; i < name.size(); i++){

            List list = new List();
            list.add((new ListItem("Номер: "+ name.get(i) +"\t", f1)));
            list.add((new ListItem("Клиент: "+ name.get(++i) +"\t", f1)));
            list.add((new ListItem("Сотрудник: "+ name.get(++i) +"\t", f1)));
            list.add((new ListItem("Вид кредита: "+ name.get(++i) +"\t", f1)));
            list.add((new ListItem("Дата начала: "+ name.get(++i) +"\t", f1)));
            list.add((new ListItem("Дата конца: "+ name.get(++i) +"\t\n\n", f1)));
            document1.add(list);
        }
        document1.close();


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
        Stage stage = (Stage) back1.getScene().getWindow();
        stage.close();
    }


}

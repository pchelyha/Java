package com.example.demo2;

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
import javafx.scene.input.DataFormat;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class history {

    private ObservableList<Data> histori = FXCollections.observableArrayList();

    DB db = new DB();

    @FXML
    private TableView<Data> tableView;
    @FXML
    public javafx.scene.control.Button back1;
    @FXML
    private TableColumn<Data, String> tableColumn1;

    @FXML
    private TableColumn<Data, String> tableColumn2;


    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        ArrayList<String> name = null;
        try {
            name = db.History();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
        for(int i = 0; i < name.size(); ++i){
            histori.add(new Data(name.get(i), name.get(++i)));
            System.out.println(histori);
        }


//Задаем тип данных переменным
        tableColumn1.setCellValueFactory(new PropertyValueFactory<Data, String>("FIO"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<Data, String>("histori"));
        tableView.setItems(histori);
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

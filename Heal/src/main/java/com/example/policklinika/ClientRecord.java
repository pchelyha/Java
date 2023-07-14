package com.example.policklinika;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientRecord {
    @FXML
    private ComboBox name_clients;
    @FXML
    private Button skode;
    @FXML
    private Button back;
    @FXML
    private ComboBox name_service;
    @FXML
    private Button b_zapis;
    @FXML
    private TextField date_priem;
    @FXML
    private TextField time_priem;
    db DB = null;
    MainPersonalAccount qwe = null;
    MainShtrihCode ggg = null;
    @FXML
    void initialize () throws SQLException, ClassNotFoundException {
        DB = new db();
        qwe = new MainPersonalAccount();
        ggg = new MainShtrihCode();
        ObservableList <String> client = FXCollections.observableList(DB.FIO_Clients());
        name_clients.setItems(client);

        ObservableList <String> service = FXCollections.observableList(DB.Name_Service());
        name_service.setItems(service);
        b_zapis.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    zxc();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            });
        back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage = new Stage();
                try {
                    qwe.start(stage);
                } catch (IOException e) {

                }
                Stage stage1 = (Stage) back.getScene().getWindow();
                stage1.close();
            }
        });
        skode.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage_s = new Stage();
                try {
                    ggg.start(stage_s);
                } catch (IOException e) {

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    void zxc () throws SQLException, ClassNotFoundException {
        int i = 0;
        int b = 0;
        ArrayList <String> incom = DB.Income_s();
        ArrayList <String> price = DB.Price();
        for (int a = 0; a < DB.FIO_Clients().size();a++) {
            if (name_clients.getValue().equals(DB.FIO_Clients().get(a))) {
                i=a+1;
            }
            if (name_service.getValue().equals(DB.Name_Service().get(a))) {
                b=a;
                int c = Integer.parseInt(incom.get(a)) + Integer.parseInt (price.get(a));
                DB.Income(c, a);
            }
        }
        DB.CreateOrder(date_priem.getText(), time_priem.getText(), i, b);
    }
}

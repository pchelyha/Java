package com.example.kursovaia;

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

public class Сlient {
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
    BD BD = null;
    MainHistory qwe = null;
    MainShtrihCode ggg = null;
    @FXML
    void initialize () throws SQLException, ClassNotFoundException {
        BD = new BD();
        qwe = new MainHistory();
        ggg = new MainShtrihCode();
        ObservableList <String> client = FXCollections.observableList(BD.FIO_Clients());
        name_clients.setItems(client);

        ObservableList <String> service = FXCollections.observableList(BD.Name_Service());
        name_service.setItems(service);
        b_zapis.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    zaa();
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

    void zaa () throws SQLException, ClassNotFoundException {
        int i = 0;
        int b = 0;
        ArrayList <String> incom = BD.Income_s();
        ArrayList <String> price = BD.Price();
        for (int a = 0; a < BD.FIO_Clients().size();a++) {
            if (name_clients.getValue().equals(BD.FIO_Clients().get(a))) {
                i=a+1;
            }
            if (name_service.getValue().equals(BD.Name_Service().get(a))) {
                b=a;
                int c = Integer.parseInt(incom.get(a)) + Integer.parseInt (price.get(a));
                BD.Income(c, a);
            }
        }
        BD.CreateOrder(date_priem.getText(), time_priem.getText(), i, b);
    }
}

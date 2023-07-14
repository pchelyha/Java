package com.example.policklinika;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ListClients {
    @FXML
    private ComboBox fio_klienta;
    @FXML
    private Button spisok;
    @FXML
    private Button vihod;
    db DB = null;
    MainPersonalAccount eee = null;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        DB = new db();
        eee = new MainPersonalAccount();
        ObservableList<String> client = FXCollections.observableList(DB.FIO_Clients());
        fio_klienta.setItems(client);

        spisok.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            }
        });
        vihod.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage_f = new Stage();
                try {
                    eee.start(stage_f);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage stage_t = (Stage) vihod.getScene().getWindow();
                stage_t.close();
            }
        });
    }
    }

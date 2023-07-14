package com.example.kursovaia;

import com.mysql.cj.util.DnsSrv;
import com.mysql.cj.xdevapi.Client;
import com.mysql.cj.xdevapi.Session;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
public class Controller  {
    @FXML
    private  TextField l;

    @FXML
    private  PasswordField p;

    public static String dannie;

    @FXML
    private CheckBox g;
    @FXML
    private TextField pa;

    @FXML
    private Label oshibka;

    @FXML
    private Button vhod;
    BD BD = null;

    MainHistory mp=null;
    int count = 0;

    @FXML
    void initialize() {
        oshibka.setVisible(false);

        // Инициируем объект
        BD = new BD();

        mp = new MainHistory();
        // Обработчик события. Сработает при нажатии на кнопку
        vhod.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Timer tm = new Timer();
                dannie = trans();
                voiti();
                if (count==3) {
                    p.setVisible(false);
                    pa.setVisible(false);
                    g.setVisible(false);
                    l.setVisible(false);
                    oshibka.setVisible(true);
                    tm.schedule(task, 10000l);

                }



            }
        });
    }

    public void mask() {

        if (g.isSelected()) {
            pa.setText(p.getText());
            p.setVisible(false);
            pa.setVisible(true);
        } else {
            p.setText(pa.getText());
            pa.setVisible(false);
            p.setVisible(true);
        }
    }

    TimerTask task = new TimerTask(){
        public void run() {
            p.setVisible(true);
            pa.setVisible(true);
            g.setVisible(true);
            l.setVisible(true);
            oshibka.setVisible(false);
        }
    };
    @FXML
    public  int voiti() {
        int b =0;
        int c =0;
        int d =0;
        int g = 0;


        try{
            String log = l.getText();
            String pass = p.getText();
            ArrayList<String> login = BD.Login();
            ArrayList<String> password = BD.Password();



            for (int i=0; i<login.size();i++) {
                if (log.equals(login.get(i))) {
                    b = 1;
                    d=i;
                }

            }
            for(int i=0; i<password.size();i++){
                if (pass.equals(password.get(i))){
                    c = 1;
                    g=i;

                }


            }

            if (b==1 && c== 1 && d==g ){
                Stage stage_e = new Stage();
                mp.start(stage_e);

                Stage stage1 = (Stage) vhod.getScene().getWindow();
                stage1.close();




                return d;


            } else {
                count++;
                return 0;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return 1;
    }

    public String trans(){
        String s = l.getText();
        return s;
    }
}
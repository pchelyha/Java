package com.example.demo2;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller  {


    @FXML
    private  TextField l;


    @FXML
    private  PasswordField p;
    @FXML
    private CheckBox glaz;
    @FXML
    private TextField pa;

    @FXML
    private Label oshibka;

    @FXML
    private  Button vhod;
    DB db = null;
//    MainShtrihCode sh = null;
    int count = 0;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

    oshibka.setVisible(false);



        // Инициируем объект
       db = new DB();
       ArrayList<String> arrayList = db.Check();
       if (arrayList.size()==0) {
           db.CreateClients();
           db.CreateEmployee();
           db.CreateLoans();
           db.CreateServices();
           db.InsertClients();
           db.InsertEmployee();
           db.InsertLoans();
           db.InsertServices();
       }
        // Обработчик события. Сработает при нажатии на кнопку
        vhod.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                try {
                    voiti();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                if (count%3==0) {
                    PauseTransition hide = new PauseTransition(Duration.seconds(0));
                    PauseTransition hide1 = new PauseTransition(Duration.seconds(0));
                    PauseTransition hide2 = new PauseTransition(Duration.seconds(10));
                    PauseTransition hide3 = new PauseTransition(Duration.seconds(10));
                    hide.setOnFinished(e -> vhod.setVisible(false));
                    hide1.setOnFinished(e -> oshibka.setVisible(true));
                    hide.play();
                    hide1.play();
                    hide2.setOnFinished(e -> vhod.setVisible(true));
                    hide3.setOnFinished(e -> oshibka.setVisible(false));
                    hide2.play();
                    hide3.play();


                }



            }
        });

    }

    public void maska() {

        if (glaz.isSelected()) {
            pa.setText(p.getText());
            p.setVisible(false);
            pa.setVisible(true);
        } else {
            p.setText(pa.getText());
            pa.setVisible(false);
            p.setVisible(true);
        }
    }


@FXML
   public  int voiti() throws IOException {
      int b =0;
        int c =0;
        int d =0;
        int g = 0;


        try{
            String log = l.getText();
            String pass = p.getText();
            ArrayList<String> login = db.Login();
            ArrayList<String> password = db.Password();
            ArrayList<String> FIO = db.FIO();
            ArrayList<String> Post = db.Post();



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
                admin.firstName =FIO.get(g);
                admin.dolgnost = Post.get(g);
                Employee.firstName =FIO.get(g);
                Employee.dolgnost = Post.get(g);
                Employee1.firstName =FIO.get(g);
                Employee1.dolgnost = Post.get(g);
                db.Update(log);
                if (Post.get(g).equals("Администратор")) {
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
                    primaryStage.setTitle("Профиль");
                    primaryStage.getIcons().add(new Image("icon.png"));
                    primaryStage.setScene(new Scene(root, 600, 400));
                    primaryStage.setResizable(false);
                    primaryStage.show();
                    Stage stage = (Stage) vhod.getScene().getWindow();
                    stage.close();
                }else if(Post.get(g).equals("Старший кредитного отдела")){
                        Stage primaryStage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("Employee.fxml"));
                        primaryStage.setTitle("Профиль");
                        primaryStage.getIcons().add(new Image("icon.png"));
                        primaryStage.setScene(new Scene(root, 600, 400));
                        primaryStage.setResizable(false);
                        primaryStage.show();
                        Stage stage = (Stage) vhod.getScene().getWindow();
                        stage.close();
                } else {
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("employee1.fxml"));
                    primaryStage.setTitle("Профиль");
                    primaryStage.getIcons().add(new Image("icon.png"));
                    primaryStage.setScene(new Scene(root, 600, 400));
                    primaryStage.setResizable(false);
                    primaryStage.show();
                    Stage stage = (Stage) vhod.getScene().getWindow();
                    stage.close();
                }



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
}
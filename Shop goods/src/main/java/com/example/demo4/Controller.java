package com.example.demo4;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;


public class Controller {
    @FXML
    private TextField field_number;
    @FXML
    private TextField field_password;
    @FXML
    private TextField field_code;
    @FXML
    private Button button_cancel;
    @FXML
    private Button button_enter;
    @FXML
    private ImageView imageView;
    DB db = null;
    public static String role_name = null;
    Main mn = new Main();
    int b = 0;

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        db = new DB();
        ArrayList<String> number = db.ID();
        ArrayList<String> pass = db.Password();
        ArrayList<String> role = db.Role_id();
        int len = 10;
        String code = generateRandomPassword(len);

        field_number.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().isWhitespaceKey()) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    for (int i = 0; i < number.size(); i++) {
                        if (field_number.getText().equals(number.get(i))) {
                            field_number.setEditable(false);
                            field_number.setDisable(true);
                            field_password.setEditable(true);
                            field_password.setDisable(false);
                            field_password.requestFocus();
                            b = i;

                        }
                    }
                }
            }
        });
        field_password.setOnKeyPressed(keyEvent1 -> {
            if (keyEvent1.getCode().isWhitespaceKey()) {
                if (keyEvent1.getCode() == KeyCode.ENTER) {
                    for (int a = 0; a < pass.size(); a++) {
                        if (field_password.getText().equals(pass.get(a))) {
                            field_password.setEditable(false);
                            field_password.setDisable(true);
                            field_code.setEditable(true);
                            field_code.setDisable(false);
                            field_code.requestFocus();
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Код доступа");
                            alert.setHeaderText(String.format("Ваш код доступа: %s", code));

                            // если пользователь нажал OK
                            Optional<ButtonType> option = alert.showAndWait();
                            if (option.get() == ButtonType.OK) {
                                alert.close();
                            }

                            System.out.println(code);

                        }
                    }
                }
            }
        });

        field_code.setOnKeyPressed(keyEvent2 -> {
            if (keyEvent2.getCode().isWhitespaceKey()) {
                if (keyEvent2.getCode() == KeyCode.ENTER) {
                    if (field_code.getText().equals(code)) {
                        field_code.setEditable(false);

                        try {
                            role_name = db.Role(Integer.valueOf(role.get(b))).get(0);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        Stage stage = new Stage();
                        try {
                            mn.start(stage);
                            Stage stage1 = (Stage) button_cancel.getScene().getWindow();
                            stage1.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }
            }

        });

        button_enter.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (field_code.getText().equals(code)) {
                    String role_name = null;
                    try {
                        role_name = db.Role(Integer.valueOf(role.get(b))).get(0);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    Stage stage = new Stage();
                    try {
                        mn.start(stage);
                        Stage stage1 = (Stage) button_cancel.getScene().getWindow();
                        stage1.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println("Вы ввели не все данные");
                }
            }
        });
        button_cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                field_code.setText(null);
                field_password.setText(null);
                field_number.setText(null);
            }
        });
    }
    public static String generateRandomPassword(int len)
    {
        // Диапазон ASCII – буквенно-цифровой (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        // каждая итерация цикла случайным образом выбирает символ из заданного
        // диапазон ASCII и добавляет его к экземпляру `StringBuilder`

        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }



}
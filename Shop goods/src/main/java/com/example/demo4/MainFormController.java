package com.example.demo4;

import com.example.demo4.Food;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.demo4.Controller.role_name;

public class MainFormController implements Initializable {
    public TableView mainTable;

    ObservableList<Food> foodList = FXCollections.observableArrayList();
    public MenuBar menu;
    DB db = new DB();
    ArrayList<String> id_food;


    {
        try {
            id_food = db.Id_food();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Objects.equals(role_name, "8")) {
            menu.setDisable(true);
        }
        try {
            ArrayList<String> name_food = db.Name();
            ArrayList<String> kal_food = db.Kkal();
            ArrayList<String> desc_food = db.Desc();
            for(int i = 0; i<name_food.size(); i++){
                foodList.add(new Food(Integer.parseInt(kal_food.get(i)), db.Name().get(i), desc_food.get(i)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // заполнили список данными




        // подключили к таблице
        mainTable.setItems(foodList);


        // создаем столбец, указываем что столбец преобразует Food в String,
        // указываем заголовок колонки как "Название"
        TableColumn<Food, String> titleColumn = new TableColumn<>("Название");
        // указываем какое поле брать у модели Food
        // в данном случае title, кстати именно в этих целях необходимо было создать getter и setter для поля title
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        // тоже самое для калорийности
        TableColumn<Food, String> kkalColumn = new TableColumn<>("Калорийность");
        kkalColumn.setCellValueFactory(new PropertyValueFactory<>("kkal"));
        TableColumn<Food, String> descriptionColumn = new TableColumn<>("Описание");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("desc"));
        // если хотим что-то более хитрое выводить, то используем лямбда выражение
       /* descriptionColumn.setCellValueFactory(cellData -> {
            // плюс надо обернуть возвращаемое значение в обертку свойство
            return new SimpleStringProperty(cellData.getValue().getDescription());
        });*/

        // добавляем сюда descriptionColumn
        mainTable.getColumns().addAll(titleColumn, kkalColumn, descriptionColumn);
    }

    public void onAddClick(ActionEvent actionEvent) throws IOException {
        // эти три строчки создюат форму из fxml файлика
        // в принципе можно было бы обойтись
        // Parent root = FXMLLoader.load(getClass().getResource("FoodForm.fxml"));
        // но дальше вот это разбиение на три строки упростит нам жизнь
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("food_form.fxml"));
        Parent root = loader.load();

        // ну а тут создаем новое окно
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        // указываем что оно модальное
        stage.initModality(Modality.WINDOW_MODAL);
        // указываем что оно должно блокировать главное окно
        // ну если точнее, то окно, на котором мы нажали на кнопку
        stage.initOwner(this.mainTable.getScene().getWindow());

        // открываем окно и ждем пока его закроют
        stage.showAndWait();
        FoodFormController controller = loader.getController();
        // проверяем что наали кнопку save
        if (controller.getModalResult()) {
            // собираем еду с формы
            Food newFood = controller.getFood();
            // добавляем в список
            this.foodList.add(newFood);
        }
    }

    public void onEditClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("food_form.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());
        FoodFormController controller = loader.getController();
        controller.setFood((Food) this.mainTable.getSelectionModel().getSelectedItem());


        stage.showAndWait();
        if (controller.getModalResult()) {
            // узнаем индекс выбранной в таблице строки

            int index = this.mainTable.getSelectionModel().getSelectedIndex();
            // подменяем строку в таблице данными на форме
            System.out.println( this.mainTable.getItems());
            this.mainTable.getItems().set(index, controller.getFood());
        }
    }

    public void onDeleteClick(ActionEvent actionEvent) {
        // берем выбранную на форме еду
        Food food = (Food) this.mainTable.getSelectionModel().getSelectedItem();

        // выдаем подтверждающее сообщение
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение");
        alert.setHeaderText(String.format("Точно удалить %s?", food.getTitle()));

        // если пользователь нажал OK
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            // удаляем строку из таблицы
            int index = this.mainTable.getSelectionModel().getSelectedIndex();

            this.mainTable.getItems().remove(food);
            try {
                db.Delete_food(Integer.valueOf(id_food.get(index)));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
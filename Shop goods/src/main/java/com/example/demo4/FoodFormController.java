package com.example.demo4;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FoodFormController  implements Initializable {
    // создаем
    public ChoiceBox cmbFoodType;
    public TextField txtFoodTitle;
    public TextField txtFoodKkal;

    public VBox fruitPane;
    public CheckBox chkIsFresh;

    public HBox chocolatePane;
    public ChoiceBox cmbChocolateType;

    public VBox cookiePane;
    public CheckBox chkWithSugar;
    public CheckBox chkWithPoppy;
    public CheckBox chkWithSesame;

    final String FOOD_FRUIT = "Фрукт";
    final String FOOD_CHOCOLATE = "Шоколадка";
    final String FOOD_COOKIE = "Булочка";
    private Boolean modalResult = false;

    DB db = new DB();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbFoodType.setItems(FXCollections.observableArrayList(
                FOOD_FRUIT,
                FOOD_CHOCOLATE,
                FOOD_COOKIE
        ));

        cmbFoodType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updatePanes((String) newValue);
            this.fruitPane.setVisible(newValue.equals(FOOD_FRUIT));
            this.fruitPane.setManaged(newValue.equals(FOOD_FRUIT)); // добавили
            this.chocolatePane.setVisible(newValue.equals(FOOD_CHOCOLATE));
            this.chocolatePane.setManaged(newValue.equals(FOOD_CHOCOLATE)); // добавили
            this.cookiePane.setVisible(newValue.equals(FOOD_COOKIE));
            this.cookiePane.setManaged(newValue.equals(FOOD_COOKIE)); // добавили
        });
        updatePanes("");
        cmbChocolateType.getItems().setAll(
                Chocolate.Type.white,
                Chocolate.Type.black,
                Chocolate.Type.milk
        );
        cmbChocolateType.setConverter(new StringConverter<Chocolate.Type>() {
            @Override
            public String toString(Chocolate.Type object) {
                // просто указываем как рендерить
                switch (object) {
                    case white:
                        return "Белый";
                    case black:
                        return "Черный";
                    case milk:
                        return "Молочный";
                }
                return null;
            }

            @Override
            public Chocolate.Type fromString(String string) {
                // этот метод не трогаем так как наш комбобкос имеет фиксированный набор элементов
                return null;
            }
        });


    }

    public void onSaveClick(ActionEvent actionEvent) {
        this.modalResult = true; // ставим результат модального окна на true
        // закрываем окно к которому привязана кнопка
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void onCancelClick(ActionEvent actionEvent) {
        this.modalResult = false; // ставим результат модального окна на false
        // закрываем окно к которому привязана кнопка
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }

    // геттер для результата модального окна
    public Boolean getModalResult() {
        return modalResult;
    }

    public void updatePanes(String value) {
        this.fruitPane.setVisible(value.equals(FOOD_FRUIT));
        this.fruitPane.setManaged(value.equals(FOOD_FRUIT));
        this.chocolatePane.setVisible(value.equals(FOOD_CHOCOLATE));
        this.chocolatePane.setManaged(value.equals(FOOD_CHOCOLATE));
        this.cookiePane.setVisible(value.equals(FOOD_COOKIE));
        this.cookiePane.setManaged(value.equals(FOOD_COOKIE));
    }

    public void setFood(Food food) {
        // делаем так что если объект редактируется, то нельзя переключать тип
        this.cmbFoodType.setDisable(food != null);
        if (food != null) {
            // ну а тут стандартное заполнение полей в соответствии с переданной едой
            this.txtFoodKkal.setText(String.valueOf(food.getKkal()));
            this.txtFoodTitle.setText(food.getTitle());

            if (food instanceof Fruit) { // если фрукт
                this.cmbFoodType.setValue(FOOD_FRUIT);
                this.chkIsFresh.setSelected(((Fruit) food).isFresh);
            } else if (food instanceof Cookie) { // если булочка
                this.cmbFoodType.setValue(FOOD_COOKIE);
                this.chkWithSugar.setSelected(((Cookie) food).withSugar);
                this.chkWithPoppy.setSelected(((Cookie) food).withPoppy);
                this.chkWithSesame.setSelected(((Cookie) food).withSesame);
            } else if (food instanceof Chocolate) { // если шоколад
                this.cmbFoodType.setValue(FOOD_CHOCOLATE);
                this.cmbChocolateType.setValue(((Chocolate) food).type);
            }
        }
    }

    public Food getFood() {
        Food result = null;
        int kkal = Integer.parseInt(this.txtFoodKkal.getText());
        String title = this.txtFoodTitle.getText();
        switch ((String) this.cmbFoodType.getValue()) {
            case FOOD_CHOCOLATE:
                String typeString = "";
                switch ((Chocolate.Type) this.cmbChocolateType.getValue()) {
                    case white:
                        typeString = "белый";
                        break;
                    case black:
                        typeString = "черный";
                        break;
                    case milk:
                        typeString = "молочный";
                        break;
                }

                String res = String.format("Шоколад %s", typeString);
                result = new Chocolate(kkal, title, res, (Chocolate.Type) this.cmbChocolateType.getValue());
                try {
                    db.Create_food(title, kkal, "Шоколад", res);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case FOOD_COOKIE:
                ArrayList<String> items = new ArrayList<>();
                if (chkWithSugar.isSelected())
                    items.add("с сахаром");
                if (chkWithPoppy.isSelected())
                    items.add("с маком");
                if (chkWithSesame.isSelected())
                    items.add("с кунжутом");

                String desc = String.format("Булочка %s", String.join(", ", items));
                result = new Cookie(
                        kkal,
                        title, desc,
                        this.chkWithSugar.isSelected(),
                        this.chkWithPoppy.isSelected(),
                        this.chkWithSesame.isSelected()
                );
                try {
                    db.Create_food(title, kkal, "Булочка", desc);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case FOOD_FRUIT:
                String isFreshString = chkIsFresh.isSelected() ? "свежий" : "не свежий";
                String resu = String.format("Фрукт %s", isFreshString);
                result = new Fruit(kkal, title, resu, this.chkIsFresh.isSelected());
                try {
                    db.Create_food(title, kkal, "Фрукт", resu);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
        }

        return result;
    }
}

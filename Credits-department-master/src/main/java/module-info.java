module com.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires itextpdf;
    requires javafx.swing;


    opens com.example.demo2 to javafx.fxml;
    exports com.example.demo2;
}
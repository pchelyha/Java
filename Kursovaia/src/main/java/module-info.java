module com.example.policklinika {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires mysql.connector.j;
    requires itextpdf;
    requires java.desktop;

    opens com.example.kursovaia to javafx.fxml;
    exports com.example.kursovaia;
}
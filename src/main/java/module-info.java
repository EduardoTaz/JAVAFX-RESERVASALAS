module com.example.reservasala {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.reservasala to javafx.fxml;
    exports com.example.reservasala;
    exports com.example.reservasala.model;
}

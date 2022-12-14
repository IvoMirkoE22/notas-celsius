module com.celsius.notas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // para que de visibilidad de la clase

    opens com.celsius.notas to javafx.fxml;

    exports com.celsius.notas;
}

module org.example.escriturarapida {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens org.example.escriturarapida to javafx.fxml;
    exports org.example.escriturarapida;
}
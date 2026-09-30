module org.example.escriturarapida {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.media;

    opens org.example.escriturarapida to javafx.fxml;
    exports org.example.escriturarapida;
}
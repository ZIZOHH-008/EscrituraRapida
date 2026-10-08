module org.example.escriturarapida {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.media;
    requires javafx.graphics;

    opens org.example.escriturarapida to javafx.fxml;
    exports org.example.escriturarapida;
    exports org.example.escriturarapida.controllers;
    opens org.example.escriturarapida.controllers to javafx.fxml;
}
module org.example.aeroportfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.media;
    requires java.desktop;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.core;
    requires static lombok;
    requires jakarta.persistence;
    opens org.example.aeroportfx.model to com.fasterxml.jackson.databind, com.fasterxml.jackson.datatype.jsr310,javafx.base;
    opens org.example.aeroportfx.controller to javafx.fxml;
    exports org.example.aeroportfx;

}
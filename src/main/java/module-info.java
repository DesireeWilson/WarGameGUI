module com.example.wargamegui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.wargamegui to javafx.fxml;
    exports com.example.wargamegui;
    exports com.example.wargamegui.controller;
    opens com.example.wargamegui.controller to javafx.fxml;
}
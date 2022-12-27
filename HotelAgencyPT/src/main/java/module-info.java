module hotel.agencypt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires java.desktop;
    requires org.json;
    requires json.simple;

    opens hotel.agencypt to javafx.fxml;
    opens Classes to javafx.base;

    exports hotel.agencypt;
    exports hotel.agencypt.Controller;
    exports Classes;
    opens hotel.agencypt.Controller to javafx.base, javafx.fxml;
    exports DataBase;
    opens DataBase to javafx.base;
}
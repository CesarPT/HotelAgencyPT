module hotel.agencypt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;

    opens hotel.agencypt to javafx.fxml;
    opens hotel.agencypt.Controller to javafx.fxml;
    exports hotel.agencypt;
    exports hotel.agencypt.Controller;
}
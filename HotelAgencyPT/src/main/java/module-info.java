module com.example.hotelagencypt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.hotelagencypt to javafx.fxml;
    exports com.example.hotelagencypt;
    exports com.example.hotelagencypt.Controllers;
    opens com.example.hotelagencypt.Controllers to javafx.fxml;
}
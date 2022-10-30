module com.example.hotelagencypt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hotelagencypt to javafx.fxml;
    exports com.example.hotelagencypt;
}
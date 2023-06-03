module com.example.aa {
    requires javafx.controls;
    requires javafx.media;
    requires javafx.fxml;
    requires org.json;
    requires json.simple;


    opens view to javafx.fxml;
    exports view;
}
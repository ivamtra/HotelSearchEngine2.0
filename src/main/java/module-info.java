module hotelsearchengine.vidmot {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens hotelsearchengine.vidmot to javafx.fxml;
    exports hotelsearchengine.vidmot;
}
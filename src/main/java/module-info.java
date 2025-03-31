module com.example.storaverkefnid {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.storaverkefnid to javafx.fxml;
    exports com.example.storaverkefnid;
}
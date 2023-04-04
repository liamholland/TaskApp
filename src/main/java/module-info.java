module com.example {
    requires javafx.controls;
    requires javafx.fxml;

    //App.java
    opens com.example to javafx.fxml;
    exports com.example;

    //Controllers for FrontEnd
    opens com.example.FrontEnd to javafx.fxml;
    exports com.example.FrontEnd;
}

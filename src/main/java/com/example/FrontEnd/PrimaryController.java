package com.example.FrontEnd;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class PrimaryController {

    @FXML private VBox main;

    @FXML
    private void createNewTask() throws IOException {
        CustomControl cc = new CustomControl("New Task");
        main.getChildren().add(cc);
    }
}

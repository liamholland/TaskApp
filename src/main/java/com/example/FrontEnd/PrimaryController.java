package com.example.FrontEnd;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

public class PrimaryController {

    @FXML private VBox taskSection;
    @FXML private Button createTaskButton;

    @FXML
    private void createNewTask() throws IOException {
        createTaskButton.setDisable(true);
        TaskComponent cc = new TaskComponent("New Task");
        taskSection.getChildren().add(cc);
    }
}

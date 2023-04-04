package com.example.FrontEnd;

import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import com.example.BackEnd.*;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class PrimaryController {

    private ArrayList<Task> tasks = new ArrayList<>();

    private void refreshTasks(){
 
    }

    @FXML private VBox main;

    @FXML
    private void createNewTask() throws IOException {
        CustomControl cc = new CustomControl("New Task");
        main.getChildren().add(cc);
        tasks.add(new Task("New Task", LocalDate.now()));
        refreshTasks();
    }
}

package com.example.FrontEnd;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import com.example.BackEnd.Task;

public class TaskComponent extends VBox {
    @FXML private Label name;
    @FXML private Label time;
    @FXML private Label description;

    public TaskComponent(Task task) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "task.fxml"));
        try {
            fxmlLoader.setLocation(new URL(
                    "file:///C:/Users/Liam/OneDrive - National University of Ireland, Galway/Coding/demo/src/main/resources/com/example/task.fxml"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        setName(task.getName());
        
        if(task.isScheduled()){
            setTime(task.getStartTime());
        }

        setDes(task.getDescription());

    }

    public void setName(String value) {
        name.textProperty().set(value);
    }

    public void setTime(String value) {
        time.textProperty().set(value);
    }

    public void setDes(String value) {
        description.textProperty().set(value);
    }
}
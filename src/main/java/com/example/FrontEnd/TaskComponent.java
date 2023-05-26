package com.example.FrontEnd;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import com.example.BackEnd.Task;

public class TaskComponent extends VBox {
    @FXML private Label name;
    @FXML private Label startTime;
    @FXML private Label endTime;
    @FXML private Label description;
    @FXML private Label completedLabel;
    @FXML private Label duration;
    @FXML private Button completeTaskButton;
    @FXML private Button deleteTaskButton;
    @FXML private HBox timeInfoArea;

    private PrimaryController parent;
    private Task task;

    public TaskComponent(Task createdTask, PrimaryController parent) {
        this.parent = parent;

        task = createdTask; //save the value of the task created in the task creator

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
            setTime(task.getStartTime(), task.getEndTime());
        }

        setDes(task.getDescription());

        setDuration(task.getDuration());

        completeTaskButton.setVisible(!task.isCompleted());
        deleteTaskButton.setVisible(!task.isCompleted());
        completedLabel.setVisible(task.isCompleted());
        timeInfoArea.setVisible(task.isScheduled());
    }

    private void setDuration(long durationInMinutes) {
        duration.textProperty().set(Long.toString(durationInMinutes) + " Minutes");
    }

    private void setName(String value) {
        name.textProperty().set(value);
    }

    private void setTime(String start, String end) {
        startTime.textProperty().set(start);
        endTime.textProperty().set(end);
    }

    private void setDes(String value) {
        description.textProperty().set(value);
    }

    @FXML
    //delete this task from its day
    private void deleteThisTask(){
        parent.deleteTask(task);
    }

    @FXML
    public void markComplete(){
        task.markComplete();
        parent.refreshCompletion();
        completeTaskButton.setVisible(false);
        deleteTaskButton.setVisible(false);
        completedLabel.setVisible(true);
    }
}
package com.example.FrontEnd;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import com.example.BackEnd.Task;
import com.example.BackEnd.Day;

public class TaskCreator extends VBox {
    @FXML private TextField name;
    @FXML private TextField time;
    @FXML private TextField description;
    @FXML private VBox main;
    @FXML private HBox component;

    public TaskCreator(PrimaryController parent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "task_creator.fxml"));
        try {
            fxmlLoader.setLocation(new URL(
                    "file:///C:/Users/Liam/OneDrive - National University of Ireland, Galway/Coding/demo/src/main/resources/com/example/task_creator.fxml"));
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

        NumberSelector hoursSelector = new NumberSelector(0, 24);
        NumberSelector minutesSelector = new NumberSelector(0, 60, null, hoursSelector);
        hoursSelector.setLesser(minutesSelector);

        component.getChildren().add(hoursSelector);
        component.getChildren().add(minutesSelector);

        name.requestFocus();

        //add an event listener to the component to check if it can be saved when it loses focus
        name.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if(!newVal){
                if(isDataValid()){
                    parent.saveTask(this);
                }
            }
        });

        description.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if(!newVal){
                if(isDataValid()){
                    parent.saveTask(this);  
                }
            }
        });
    }

    private boolean isDataValid(){
        String nameText = name.textProperty().get();
        String desText = description.textProperty().get();

        return !(desText == null || desText.equals("")) && !(nameText == null || nameText.equals(""));
    }

    //turn the creator into an actual task
    public Task getTask(Day day){
        if(!isDataValid()){
            return null;
        }

        //data is valid
        return new Task(name.textProperty().get(), description.textProperty().get(), day);
    }
}
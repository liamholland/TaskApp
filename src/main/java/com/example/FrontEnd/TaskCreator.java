package com.example.FrontEnd;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.CheckBox;

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
    @FXML private CheckBox scheduleBox;
    @FXML private NumberSelector hoursSelector;
    @FXML private NumberSelector minutesSelector;

    private PrimaryController parent;

    public TaskCreator(PrimaryController parent) {
        this.parent = parent;
        
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

        hoursSelector = new NumberSelector(0, 24);
        minutesSelector = new NumberSelector(0, 60, hoursSelector);

        hoursSelector.setVisible(false);
        minutesSelector.setVisible(false);

        component.getChildren().add(hoursSelector);
        component.getChildren().add(minutesSelector);

        name.requestFocus();     

        scheduleBox.setOnAction(e -> toggleTimeSelector());
        scheduleBox.setOnAction(e -> toggleTimeSelector());
    }

    private void toggleTimeSelector(){
        hoursSelector.setVisible(scheduleBox.isSelected());
        minutesSelector.setVisible(scheduleBox.isSelected());
    }

    //checks if the data in the task creator is valid
    //maybe make this react to an event and enable/disable the save button if the data is valid
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

    //save a task
    @FXML
    public void saveTask(){
        if(isDataValid()){
            this.parent.saveTask(this);
        }
    }
}
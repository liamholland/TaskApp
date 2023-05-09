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
import java.time.LocalTime;
import com.example.BackEnd.Task;
import javafx.scene.control.Label;
import com.example.BackEnd.Day;

public class TaskCreator extends VBox {
    @FXML private TextField name;
    @FXML private TextField time;
    @FXML private TextField description;
    @FXML private VBox main;
    @FXML private HBox timeSelector;
    @FXML private CheckBox scheduleBox;
    @FXML private NumberSelector hoursSelector;
    @FXML private NumberSelector minutesSelector;
    @FXML private NumberSelector durationSelector;
    @FXML private Label durationLabel;

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
        durationSelector = new NumberSelector(0, 1440);

        timeSelector.setVisible(false);

        timeSelector.getChildren().add(new Label("Schedule at"));
        timeSelector.getChildren().add(hoursSelector);
        timeSelector.getChildren().add(new Label(":"));
        timeSelector.getChildren().add(minutesSelector);
        timeSelector.getChildren().add(new Label("for"));
        timeSelector.getChildren().add(durationSelector);
        timeSelector.getChildren().add(new Label("minutes"));

        name.requestFocus();     

        scheduleBox.setOnAction(e -> timeSelector.setVisible(scheduleBox.isSelected())); //add event handler to the checkbox to toggle visibility
    }

    //checks if the data in the task creator is valid
    //maybe make this react to an event and enable/disable the save button if the data is valid
    private boolean isDataValid(){
        String nameText = name.textProperty().get();
        String desText = description.textProperty().get();

        boolean descriptionIsValid = !(desText == null || desText.equals(""));
        boolean nameIsValid = !(nameText == null || nameText.equals(""));
        boolean timeIsValid = (LocalTime.of(hoursSelector.getCurrent(), minutesSelector.getCurrent()).plusMinutes(durationSelector.getCurrent()).isAfter(LocalTime.now()));

        return descriptionIsValid && nameIsValid && timeIsValid;
    }

    //turn the creator into an actual task
    public Task getTask(Day day){
        if(!isDataValid()){
            return null;
        }

        //data is valid
        Task task = new Task(name.textProperty().get(), description.textProperty().get(), day);

        if(scheduleBox.isSelected()){
            task.scheduleFor(LocalTime.of(hoursSelector.getCurrent(), minutesSelector.getCurrent()), durationSelector.getCurrent());
        }

        return task;
    }

    //save a task
    @FXML
    public void saveTask(){
        if(isDataValid()){
            this.parent.saveTask(this);
        }
    }
}
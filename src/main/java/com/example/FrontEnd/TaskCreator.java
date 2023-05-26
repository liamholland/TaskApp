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

public class TaskCreator extends VBox {
    @FXML private TextField name;
    @FXML private TextField time;
    @FXML private TextField description;
    @FXML private VBox main;
    @FXML private HBox timeSelectorArea;
    @FXML private HBox durationSelectorArea;
    @FXML private HBox dayPatternSelectorArea;
    @FXML private CheckBox scheduleBox;
    @FXML private CheckBox repeatBox;
    @FXML private NumberSelector hoursSelector;
    @FXML private NumberSelector minutesSelector;
    @FXML private NumberSelector durationSelector;
    @FXML private NumberSelector numDaySelector;
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

        //set up the scheduler
        hoursSelector = new NumberSelector(0, 24);
        minutesSelector = new NumberSelector(0, 60, hoursSelector);
        durationSelector = new NumberSelector(0, 1440); //number of minutes in 24 hours

        durationSelectorArea.getChildren().add(new Label("Spend"));
        durationSelectorArea.getChildren().add(durationSelector);
        durationSelectorArea.getChildren().add(new Label("minutes on this task"));

        timeSelectorArea.setVisible(false);

        timeSelectorArea.getChildren().add(new Label("Schedule at"));
        timeSelectorArea.getChildren().add(hoursSelector);
        timeSelectorArea.getChildren().add(new Label(" : "));
        timeSelectorArea.getChildren().add(minutesSelector);

        numDaySelector = new NumberSelector(1, 7);

        dayPatternSelectorArea.setVisible(false);

        dayPatternSelectorArea.getChildren().add(new Label("Repeat every"));
        dayPatternSelectorArea.getChildren().add(numDaySelector);
        dayPatternSelectorArea.getChildren().add(new Label("days"));

        name.requestFocus();     

        //add event handlers to the relevant checkboxes to toggle visibility
        scheduleBox.setOnAction(e -> timeSelectorArea.setVisible(scheduleBox.isSelected()));
        repeatBox.setOnAction(e -> dayPatternSelectorArea.setVisible(repeatBox.isSelected()));
    }

    //checks if the data in the task creator is valid
    //maybe make this react to an event and enable/disable the save button if the data is valid
    private boolean isDataValid(){
        String nameText = name.textProperty().get();
        String desText = description.textProperty().get();

        boolean nameIsValid = !(nameText == null || nameText.equals(""));   //is there valid text in the name box
        boolean descriptionIsValid = !(desText == null || desText.equals(""));  //is there valid text in the description box

        //this prevents both times before the current time and also times which might stray into the next day
        boolean timeIsValid = !scheduleBox.isSelected() || (LocalTime.of(hoursSelector.getCurrent(), minutesSelector.getCurrent()).plusMinutes(durationSelector.getCurrent()).isAfter(LocalTime.now()));

        return nameIsValid && descriptionIsValid && timeIsValid;
    }

    //turn the creator into an actual task
    public Task convertToTask(){
        if(!isDataValid()){
            return null;
        }

        //data is valid
        Task task = new Task(name.textProperty().get(), description.textProperty().get());

        if(scheduleBox.isSelected()){
            task.scheduleFor(LocalTime.of(hoursSelector.getCurrent(), minutesSelector.getCurrent()), durationSelector.getCurrent());
        }
        else{
            task.setDuration(durationSelector.getCurrent());
        }

        if(repeatBox.isSelected()){
            task.markRepetitive(true, numDaySelector.getCurrent());
        }

        return task;
    }

    //save a task
    @FXML
    private void saveTask(){
        if(isDataValid()){
            parent.saveTask(this);
        }
    }
}
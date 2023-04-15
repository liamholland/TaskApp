package com.example.FrontEnd;

import com.example.BackEnd.*;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

//main frontend controller that manages what is displayed to the user
public class PrimaryController {

    //the task display section of the application
    @FXML private VBox taskSection;
    @FXML private Button createTaskButton;

    //completion section
    @FXML private Label currentDateLabel;

    private DayViewer dayViewer;    //reference to the backend api
    
    //allows child elements to save a task
    public void saveTask(TaskCreator creator){
        dayViewer.addTask(creator.getTask(dayViewer.getCurrentDay() == null ? new Day(dayViewer.getCurrentDate()) : dayViewer.getCurrentDay()));
        dayViewer.saveDay();
        taskSection.getChildren().remove(creator);
        displayTasksForDay();
        createTaskButton.setDisable(false);
    }

    public void setDayViewer(DayViewer d){
        dayViewer = d;
    }

    @FXML
    private void createNewTask() {
        createTaskButton.setDisable(true);
        TaskCreator creator = new TaskCreator(this);
        taskSection.getChildren().add(creator);
    }

    @FXML
    public void displayTasksForDay(){
        taskSection.getChildren().clear();
        currentDateLabel.textProperty().set(dayViewer.getCurrentDateAsString());
        if(dayViewer.getCurrentDay() != null){
            Task[] tasks = dayViewer.getCurrentDay().getTasks();
            for (Task task : tasks) {
                TaskComponent component = new TaskComponent(task);
                taskSection.getChildren().add(component);
            }  
        }
    }

    @FXML
    private void goToPreviousDay(){
        dayViewer.lastDay();
        displayTasksForDay();
    }

    @FXML
    private void goToNextDay(){
        dayViewer.nextDay();
        displayTasksForDay();
    }
}

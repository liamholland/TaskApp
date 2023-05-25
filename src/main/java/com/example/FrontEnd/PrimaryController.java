package com.example.FrontEnd;

import java.time.LocalDate;

import com.example.BackEnd.*;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;

//main frontend controller that manages what is displayed to the user
public class PrimaryController {

    //the task display section of the application
    @FXML private VBox taskSection;
    @FXML private Button createTaskButton;

    //completion section
    @FXML private Label currentDateLabel;
    @FXML private Label completionPercentageLabel;
    @FXML private DatePicker calendar;

    private DayViewer dayViewer;    //reference to the backend api interface

    //allows child elements to save a task
    public void saveTask(TaskCreator creator){
        //add a task, provided by the task creator, with the current day, which may have to be created
        dayViewer.addTask(creator.convertToTask());

        //remove the creator from the gui
        taskSection.getChildren().remove(creator);

        //refresh the display
        displayTasksForDay();

        //enable the task creation button
        createTaskButton.setDisable(false);
    }

    public void deleteTask(Task t){
        dayViewer.removeTask(t);
        displayTasksForDay();
    }

    //used at initialisation to get a reference to the backend api
    public void setDayViewer(DayViewer d){
        dayViewer = d;
    }

    public void refreshCompletion(){
        dayViewer.saveCurrentDay();
        dayViewer.getCurrentDay().recalculateCompletion();
        completionPercentageLabel.textProperty().set(String.format("%.2f", dayViewer.getCurrentDay().getCompletionPercentage()));
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
                TaskComponent component = new TaskComponent(task, this);
                taskSection.getChildren().add(component);
            }
            dayViewer.getCurrentDay().recalculateCompletion();
            refreshCompletion();
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

    @FXML
    private void jumpToSelectedDay(){
        LocalDate date = calendar.getValue();
        dayViewer.jumpToDate(date);
        displayTasksForDay();
    }
}

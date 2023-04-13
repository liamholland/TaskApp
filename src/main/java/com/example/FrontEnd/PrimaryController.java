package com.example.FrontEnd;

import com.example.BackEnd.*;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

public class PrimaryController {

    @FXML private VBox taskSection;
    @FXML private Button createTaskButton;

    private DayViewer dayViewer;
    
    public void saveTask(TaskCreator creator){
        dayViewer.addTask(creator.getTask(dayViewer.getCurrentDay()));
        dayViewer.saveDay();
        taskSection.getChildren().remove(creator);
        displayTasksForDay();
        createTaskButton.setDisable(false);
    }

    @FXML
    private void createNewTask() {
        createTaskButton.setDisable(true);
        TaskCreator creator = new TaskCreator(this);
        taskSection.getChildren().add(creator);
    }

    @FXML
    public void displayTasksForDay(){
        if(dayViewer.getCurrentDay() != null){
            taskSection.getChildren().clear();
            Task[] tasks = dayViewer.getCurrentDay().getTasks();
            for (Task task : tasks) {
                TaskComponent component = new TaskComponent(task);
                taskSection.getChildren().add(component);
            }  
        }
    }

    public void setDayViewer(DayViewer d){
        dayViewer = d;
    }
}

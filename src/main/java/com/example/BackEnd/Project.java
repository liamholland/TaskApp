package com.example.BackEnd;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Hashtable;

//collections of tasks related to a single project
//should be able to operate like sprints and allow you to plan them
//access from the day viewer in order to add the various tasks to the correct days
public class Project implements Serializable{
    private Hashtable<String, LocalDate> tasks;    //list of tasks and their associated date they are scheduled for
    // private double completionPercentage;

    public Project(){
        // completionPercentage = 0.0;
    }

    //add a task to a project
    public void addTaskToProject(Task task){
        //check if the task is already in the project
        if(!(tasks.keySet().contains(task.getName()))){
            task.setProject(this);  //assign this task to the project
            tasks.put(task.getName(), task.getDay().getDate());
        }
    }

    public String[] getTasks(){
        return tasks.keySet().toArray(new String[0]);
    }

    public LocalDate[] getDates(){
        return tasks.entrySet().toArray(new LocalDate[0]);
    }

    // public double getCompletion(){
    //     return completionPercentage;
    // }
}

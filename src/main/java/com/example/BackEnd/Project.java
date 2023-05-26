package com.example.BackEnd;

import java.io.Serializable;
import java.util.Hashtable;

//collections of tasks related to a single project
//should be able to operate like sprints and allow you to plan them
//access from the day viewer in order to add the various tasks to the correct days
public class Project implements Serializable{
    private Hashtable<Task, Integer> tasksWithDurations;    //list of tasks and their associated estimated duration in days
    private double completionPercentage;

    public Project(){
        completionPercentage = 0.0;
    }

    //add a task to a project
    public void addTaskToProject(Task task, int durationInDays){
        if(!tasksWithDurations.keySet().contains(task)){
            tasksWithDurations.put(task, durationInDays);
            task.setProject(this);
            recalculatePrecentageCompletion();
        }
    }

    public Task[] getTasks(){
        return tasksWithDurations.keySet().toArray(new Task[0]);
    }

    public double getCompletion(){
        return completionPercentage;
    }

    private void recalculatePrecentageCompletion(){
        int numCompleted = 0;
        for(Task t : tasksWithDurations.keySet()){
            if(t.isCompleted()) numCompleted++;
        }
        completionPercentage = (numCompleted / tasksWithDurations.size()) * 100;
    }
}

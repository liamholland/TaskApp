package com.example.BackEnd;

import java.io.Serializable;
import java.time.*;

//object created by the user to track a specific activity
public class Task implements Serializable{
    //Is an object that can be created and scheduled on a certain date and time
    private String taskName;
    private Day taskDay;     //date of the task
    private boolean scheduled;    //is the task scheduled for a certain time
    private boolean repetitive;   //is the task repeated every day
    private LocalTime taskTime;     //start time of the task - used if scheduled
    private long numMinutes;         //number of minutes to be spent on the task
    private boolean complete;   //has the task been completed
    
    public Task(String name, Day day){
        taskName = name;
        taskDay = day;
        complete = false;
    }

    public Task(String name, Day day, LocalTime time, long duration){
        this(name, day);
        taskTime = time;
        numMinutes = duration;
        scheduled = true;
    }

    public String getName(){
        return taskName;
    }

    //is the task repetitive - i.e. does it occur every day
    public boolean isRepetitive(){
        return repetitive;
    }

    public void markRepetitive(){
        repetitive = true;
    }

    public boolean isCompleted(){
        return complete;
    }

    public void markComplete(){
        complete = true;
    }

    //is the task scheduled
    public boolean isScheduled(){
        return scheduled;
    }

    //used to schedule a task for a certain time
    public void scheduleFor(LocalTime time){
        taskTime = time;
        scheduled = true;
    }

    public Day getDay(){
        return taskDay;
    }

    //start time of the task
    public String getStartTime(){
        return taskTime.toString();
    }

    //end time of the task
    public String getEndTime(){
        return taskTime.plusMinutes(numMinutes).toString();
    }

    @Override
    public String toString(){
        return String.format("%s %s", taskName, scheduled ? String.format("on %s @ %s until %s", taskDay.getDay(), getStartTime(), getEndTime()) : "Unscheduled");
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Task)){
            return false;
        }

        Task compTask = (Task)o;
        return (compTask.getName().equals(taskName) && compTask.getDay().equals(taskDay));
    }
}

package com.example.BackEnd;

import java.time.*;

//object created by the user to track a specific activity
public class Task {
    //Is an object that can be created and scheduled on a certain date and time
    private String taskName;
    private LocalDate taskDate;     //date of the task
    private boolean scheduled;    //is the task scheduled for a certain time
    private boolean repetitive;   //is the task repeated every day
    private LocalTime taskTime;     //start time of the task - used if scheduled
    private long numMinutes;         //number of minutes to be spent on the task

    public Task(String name, LocalDate date){
        taskName = name;
        taskDate = date;
    }

    public Task(String name, LocalDate date, LocalTime time, long duration){
        this(name, date);
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

    //is the task scheduled
    public boolean isScheduled(){
        return scheduled;
    }

    //used to schedule a task for a certain time
    public void scheduleFor(LocalTime time){
        taskTime = time;
        scheduled = true;
    }

    //start time of the task
    public String getStartTime(){
        return taskTime.toString();
    }

    //end time of the task
    public String getEndTime(){
        return taskTime.plusMinutes(numMinutes).toString();
    }
}

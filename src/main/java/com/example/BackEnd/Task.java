package com.example.BackEnd;

import java.io.Serializable;
import java.time.*;

//object created by the user to track a specific activity
public class Task implements Serializable{
    //Is an object that can be created and scheduled on a certain date and time
    private String taskName;
    private String taskDescription;

    private Day taskDay;     //day of the task

    private boolean scheduled;    //is the task scheduled for a certain time
    private boolean repetitive;   //is the task repeated every certain number of days for a month (30 days)
    private int numDays;    //number of days on which the task will appear again
    private LocalTime taskTime;     //start time of the task - used if scheduled
    private long numMinutes;         //number of minutes to be spent on the task

    private boolean complete;   //has the task been completed

    private boolean belongsToProject;   //does the task belong to a project
    private Project project;    //if so, which one

    public Task(String name, String description){
        taskName = name;
        taskDescription = description;
        complete = false;
    }

    public Task(String name, String description, Day day){
        this(name, description);
        taskDay = day;
    }

    public Task(String name, String description, Day day, LocalTime time, long duration, Project project){
        this(name, description, day);
        taskTime = time;
        numMinutes = duration;
        scheduled = true;
        belongsToProject = true;
        this.project = project;
    }

    public String getName(){
        return taskName;
    }

    public void setProject(Project project){
        this.project = project;
    }

    public Project getProject(){
        return project;
    }

    public boolean belongsToProject(){
        return belongsToProject;
    }

    //is the task repetitive - i.e. does it occur every day
    public boolean isRepetitive(){
        return repetitive;
    }

    public void markRepetitive(boolean value){
        repetitive = value;
    }

    public void markRepetitive(boolean value, int num){
        numDays = num;
        markRepetitive(value);
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
    public void scheduleFor(LocalTime time, int durationInMinutes){
        taskTime = time;
        scheduled = true;
        setDuration(durationInMinutes);
    }

    public void setDuration(int durationInMinutes){
        numMinutes = durationInMinutes;
    }

    public long getDuration(){
        return numMinutes;
    }

    public void setDay(Day day){
        taskDay = day;
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

    public String getDescription(){
        return taskDescription;
    }

    public void setDescription(String d){
        if(d != null){
            taskDescription = d;
        }
    }

    public int getRepeatPattern(){
        return repetitive == false ? -1 : numDays;   //return -1 if the task is not repetitive
    }

    @Override
    public String toString(){
        return String.format("%s %s", taskName, scheduled ? String.format("on %s @ %s until %s", taskDay.getDay(), getStartTime(), getEndTime()) : "Unscheduled");
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Task) || o == null){
            return false;
        }

        Task compTask = (Task)o;
        return (compTask.getName().equals(taskName) && compTask.getDay().equals(taskDay));
    }
}

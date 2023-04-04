package com.example.BackEnd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Day {
    private ArrayList<Task> tasks;  //the tasks assciated with this day
    private Date dateOfDay; //the date of the day

    public Day(Date date) {
        tasks = new ArrayList<Task>();
        dateOfDay = date;
    }

    //return all of the tasks of the current day
    public Object[] getTasks(){
        return tasks.toArray();
    }

    //add a task to the day
    public void addTask(Task t){
        if(!tasks.contains(t)){
            tasks.add(t);
        }
    }

    //remove a task from the current day
    public void removeTask(Task t){
        if(tasks.contains(t) && !t.isRepetitive()){
            tasks.remove(t);
        }
    }

    //gets the name of the day as a string
    public String getDay(){
        return new SimpleDateFormat("E").format(dateOfDay);
    }

    //get the full day and date as a string
    public String getDateAndDay(){
        return new SimpleDateFormat("EE dd/MM/yy").format(dateOfDay);
    }
}

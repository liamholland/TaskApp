package com.example.BackEnd;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class Day {
    private ArrayList<Task> tasks;  //the tasks assciated with this day
    private final LocalDate dateOfDay; //the date of the day

    public Day(LocalDate date) {
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
        return dateOfDay.getDayOfWeek().toString();
    }

    //gets the date object of the Day
    public LocalDate getDate(){
        return dateOfDay;
    }

    //gets the full day and date as a string
    public String getDateAndDay(){
        return dateOfDay.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    }

    //is this day today
    public boolean isToday(){
        return LocalDate.now().equals(dateOfDay);
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Day)){
            return false;
        }

        Day compDay = (Day)o;
        return compDay.getDate().equals(dateOfDay);
    }
}

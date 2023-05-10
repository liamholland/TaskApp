package com.example.BackEnd;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class Day implements Serializable{
    private ArrayList<Task> tasks;  //the tasks assciated with this day
    private final LocalDate dateOfDay; //the date of the day
    private double completionPercentage = 0.0;
    
    //fields to hold linked list references
    private Day before;
    private Day after;
    
    public Day(LocalDate date) {
        tasks = new ArrayList<Task>();
        dateOfDay = date;
    }

    /*
     * TASK MANAGEMENT
     */

    //return all of the tasks of the current day
    public Task[] getTasks(){
        return tasks.toArray(new Task[0]);
    }

    //add a task to the day
    public void addTask(Task t){
        if(!tasks.contains(t)){
            tasks.add(t);
            recalculateCompletion();
        }
    }

    //remove a task from the current day
    public void removeTask(Task t){
        if(tasks.contains(t) && !t.isRepetitive()){
            tasks.remove(t);
            recalculateCompletion();
        }
    }

    public void recalculateCompletion(){
        if(tasks.isEmpty()){
            completionPercentage = 0;
        }
        else{
            double numCompleted = 0;
            for(Task t : tasks){
                if(t.isCompleted()){
                    numCompleted++;
                }
            }
            completionPercentage = (numCompleted / tasks.size()) * 100;
        }
    }

    public double getCompletionPercentage(){
        return completionPercentage;
    }

    /*
     * DATE MANAGEMENT
     */

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

    //get the day that comes before this one
    public Day before(){
        return before;
    }

    public Day after(){
        return after;
    }

    public void setBefore(Day day){
        if(day == null){
            before = null;
        }
        else if(day.getDate().isBefore(dateOfDay)){
            before = day;
        }
        
    }

    public void setAfter(Day day){
        if(day == null){
            after = null;
        }
        else if(day.getDate().isAfter(dateOfDay)){
            after = day;
        }
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Day)){
            return false;
        }

        Day compDay = (Day)o;
        return compDay.getDate().equals(dateOfDay);
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Day: ");
        s.append(getDateAndDay());
        s.append("\n");
        s.append("Tasks: \n");
        for(Task t : tasks){
            s.append(t);
            s.append("\n");
        }

        return s.toString();
    }
}

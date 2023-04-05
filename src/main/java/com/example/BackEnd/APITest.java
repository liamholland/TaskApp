package com.example.BackEnd;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class APITest {
    
    public void test(){
        LocalDate today = LocalDate.now();  //get the current date
        LocalDate yesterday = LocalDate.now().minus(1, ChronoUnit.DAYS);

        //create days
        Day newDay = new Day(today);    
        Day oldDay = new Day(yesterday);


        System.out.println(newDay.isToday());   //is the day today
        System.out.println(oldDay.isToday());   //is yesterday today

        //add a couple of tasks
        newDay.addTask(new Task("New Task", newDay));
        newDay.addTask(new Task("Second Task", newDay, LocalTime.of(23, 0), 60));
        newDay.addTask(new Task("New Task", newDay));
        
        //print out the day you are on
        System.out.println(newDay.getDay());
        System.out.println(newDay.getDateAndDay());

        //print out all the tasks
        Object[] tasks = newDay.getTasks();
        for(Object t : tasks){
            System.out.println((Task)t);
        }
    }
    
    
    //application must load a day in the future and the past differently
    //application must be able to save the data attributed to a day to a file
    //application must be able to load data attributed to a day from a file

    // you must be able to edit data of the present day or days in the future
    // retitive tasks should not be written to every day - seperate file?
}

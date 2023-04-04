package com.example.BackEnd;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class APITest {
    
    public void test(){
        Date today = Date.from(Instant.now());  //get the current date
        
        Day newDay = new Day(today);    //create a new day

        //add a couple of tasks
        newDay.addTask(new Task("New Task", LocalDate.now()));
        newDay.addTask(new Task("Second Task", LocalDate.now(), LocalTime.of(23, 0), 60));
        
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
    
   // a DayViewer object can handle the data currently displayed
   // the data can be edited on the page, without writing it
   // compression for days in the past? 
}

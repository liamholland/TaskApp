package com.example.BackEnd;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

//this file is used to test the various pieces of functionality of the API during development
//will not be used in the final version as the API will be implemented through the frontend
public class APITest {
    
    public void test(){
        DayViewer dayViewer = new DayViewer();

        System.out.println(dayViewer);
    }
    

    // you must be able to edit data of the present day or days in the future
    // retitive tasks should not be written to every day - seperate file?
}

package com.example.BackEnd;

import java.time.LocalDate;

public class DayViewer {
    // what the user will interact with
    // you can make Day objects all you want but how does the user actually traverse
    // them?
    // this is the answer
    // next and previous, checking where the day is and making calls to save and
    // load functions should be done from here
    // the data can be edited on the page, without writing it to a file

    private Day currentDay; // the current day that is being viewed
    private LocalDate currentDate;

    //constructor - load the current day on initialisation
    public DayViewer() {
        currentDay = Store.read();  //read the current day from the store

        if(currentDay == null){
            currentDay = new Day(LocalDate.now());  //make a new day if nothing has been saved
        }
        else if(currentDay.getDate().isBefore(LocalDate.now())){
            currentDay = goToDate(LocalDate.now());  //go to the current date if the loaded day is in the past
        }
    }

    public String getCurrentDate(){
        return currentDate.toString();
    }

    public Day goToDate(LocalDate date){
        Day day = currentDay;   //create a new day and start with the current date
        
        //while the day is before the target date
        while (day.getDate().isBefore(date)) {
            //if there is no next day you have gone as far as possible
            if(currentDay.after() == null){
                break;
            }
            
            day = currentDay.after();   // otherwise go to the next day
        }
        
        //if you have not landed on the target day
        //this occurs either if there are not enough days to get to the date or the date has been overshot
        if (!day.getDate().equals(date)) {
            currentDate = date; //set the date to the target date
            day = null; //set the day to null
        }
        else{
            currentDate = day.getDate();    //otherwise set the date to the date of the new currentday
        }

        return day; //return the day, which could be null
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(String.format("Today is %s\nTasks:\n", currentDay.getDateAndDay()));
        if(currentDay == null){
            for(Task t : currentDay.getTasks()){
                s.append(t);
                s.append("\n");
            }
        }
        else{
            s.append("None\n");
        }
        return s.toString();
    }
}

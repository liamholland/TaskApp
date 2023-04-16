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
        currentDay = Store.load();  //read the current day from the store

        //go to the current date if the loaded day is null or not the current date
        if(currentDay == null || !currentDay.getDate().equals(LocalDate.now())){
            currentDay = goToDate(LocalDate.now());
        }

        currentDate = LocalDate.now();
    }

    //create a day object and save it
    private void createDay(){
        Day saveDay = new Day(currentDate);

        Day searchPoint = Store.load(); //start at the last saved day

        //insert it into the linked list
        if(searchPoint == null){   //if there is no linked list
            currentDay = saveDay;    //just set current day and return
            Store.save(currentDay);
            return;
        }
        
        //else insert it into the linked list in the correct position
        //this involves finding where to place the node

        //if the last saved day is before the current date, i need to go forward until i reach the day before the current date
        if(searchPoint.getDate().isBefore(currentDate)){
            //if there is no day after, the saveDay is the new after, i.e end of the linked list
            if(searchPoint.after() != null){
                //otherwise, i need to go forward until there is no next node, or the next node is too far
                while(searchPoint.after() != null && searchPoint.after().getDate().isBefore(currentDate)){
                    searchPoint = searchPoint.after();
                }
                
                //if the searchpoint is 
                if(searchPoint.after() != null){
                    saveDay.setAfter(searchPoint.after());
                    searchPoint.after().setBefore(saveDay);
                }
            }

            saveDay.setBefore(searchPoint); //set the before to the searchpoint
            searchPoint.setAfter(saveDay);
        }
        else if(searchPoint.getDate().isAfter(currentDate)){
            if(searchPoint.before() != null){
                while(searchPoint.before() != null && searchPoint.before().getDate().isAfter(currentDate)){
                   searchPoint = searchPoint.before();
                }

                if(searchPoint.before() != null){
                    saveDay.setBefore(searchPoint.before());
                    searchPoint.before().setAfter(saveDay);
                }    
            }

            saveDay.setAfter(searchPoint);
            searchPoint.setBefore(saveDay);
        }

        //set the current day to the created day
        currentDay = saveDay;

        //save the linked list back to the save file
        Store.save(currentDay);
    }

    //add a task to the current day
    public void addTask(Task task){
        if(currentDay == null){
            createDay();
        }
        
        currentDay.addTask(task);
        Store.save(currentDay);
    }

    //remove a task from the day
    public void removeTask(Task task){
        if(currentDay != null){
            currentDay.removeTask(task);
         
            if(currentDay.getTasks().length == 0){
                //the day needs to be deleted from the linked list
                if(currentDay.before() != null){
                    currentDay.before().setAfter(currentDay.after());
                }

                if(currentDay.after() != null){
                    currentDay.after().setBefore(currentDay.before());
                }
                
                Day saveDay = currentDay.before() == null ? currentDay.after() : currentDay.before();   //doesnt really matter which

                Store.save(saveDay);
             
                currentDay = null;
            }
            else{
                Store.save(currentDay);
            }
        }
    }

    //goes forward by one day
    public void nextDay(){
        currentDay = goToDate(currentDate.plusDays(1));
    }

    //goes back one day
    public void lastDay(){
        currentDay = goToDate(currentDate.minusDays(1));
    }

    public void jumpToToday(){
        currentDay = goToDate(LocalDate.now());
    }
    
    //jump to a specified date and get the save if there is one
    private Day goToDate(LocalDate targetDate){
        Day day = currentDay == null ? Store.load() : currentDay;   //create a new day and start with the current date or the last saved day
        currentDay = null;  //reset the current day

        if(day == null){
            currentDate = targetDate; //set the date to the target date
            return day; // this is null
        }

        //day != null

        if(day.getDate().isAfter(targetDate)){  //if the targetdate is before the current date we want to go backwards
            while(day.getDate().isAfter(targetDate) && day.before() != null){
                day = day.before();
            }
        }
        else if(day.getDate().isBefore(targetDate)){    //if the targetdate is after the current date we want to go forwards
            while(day.getDate().isBefore(targetDate) && day.after() != null){
                day = day.after();
            }
        }
        else{   //you are on the correct day
            currentDate = day.getDate();
            return day;
        }

        //if you have not landed on the target day
        //this occurs either if there are not enough days to get to the date or the date has been overshot
        if (!day.getDate().equals(targetDate)) {
            currentDate = targetDate; //set the date to the target date
            day = null; //set the day to null
        }
        else{
            currentDate = day.getDate();    //otherwise set the date to the date of the new currentday
        }
        
        return day; //return the day, which could be null
    }
    
    //get the current date as a string
    public String getCurrentDateAsString(){
        return currentDate.toString();
    }

    public LocalDate getCurrentDate(){
        return currentDate;
    }

    public Day getCurrentDay(){
        return currentDay;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Viewing ");
        
        if(currentDay != null){
            if(currentDay.isToday()){
                s.append("Today\nTasks:\n");
            }
            else{
                s.append(String.format("%s\nTasks:\n", currentDay.getDateAndDay()));
            }

            Task[] tasks = currentDay.getTasks();
            if(tasks.length > 0){
                for(Task t : currentDay.getTasks()){
                    s.append(t);
                    s.append("\n");
                }
            }
            else{
                s.append("None");
            }
        }
        else{
            s.append(String.format("%s\n", currentDate.equals(LocalDate.now()) ? "Today" : getCurrentDateAsString()));

            s.append("None Because day is null\n");
        }
        return s.toString();
    }
}

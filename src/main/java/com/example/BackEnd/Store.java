package com.example.BackEnd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class Store {

    //path to make saves to
    //TODO: figure out relative save path at some point
    private static final String savePath = "C:/Users/Liam/OneDrive - National University of Ireland, Galway/Coding/demo/src/main/java/com/example/BackEnd/saves/";
    
    //save a day to the file
    //this basically saves everything as Days implement a linked list type functionality
    public static void saveDays(Day day){
        try{
            FileOutputStream fStream = new FileOutputStream(savePath + "daysSave.txt");
            ObjectOutputStream oStream = new ObjectOutputStream(fStream);
            oStream.writeObject(day);
            oStream.close();
            fStream.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    //read the saved data from the file
    //it will return a day, which could be anywhere in the linked list
    //the assumption is that this will be closest to the current date
    //for example, if last visited the day before, i would only have to traverse one day to get to the current date
    public static Day loadDays(){
        try{
            FileInputStream fStream = new FileInputStream(savePath + "daysSave.txt");
            ObjectInputStream oStream = new ObjectInputStream(fStream);
            Day day = (Day)oStream.readObject();
            oStream.close();
            fStream.close();
            return day;
        }catch(Exception e){
            System.out.println("No Save File");
            return null;
        }
    }

    //save a collection of Project objects
    public static void saveProjects(Project[] projects){
        try{
            FileOutputStream fStream = new FileOutputStream(savePath + "projectsSave.txt");
            ObjectOutputStream oStream = new ObjectOutputStream(fStream);
            oStream.writeObject(projects);
            oStream.close();
            fStream.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    //load a collection of objects
    public static Project[] loadProjects(){
        try{
            FileInputStream fStream = new FileInputStream(savePath + "projectsSave.txt");
            ObjectInputStream oStream = new ObjectInputStream(fStream);
            Project[] projects = (Project[])oStream.readObject();
            oStream.close();
            fStream.close();
            return projects;
        }catch(Exception e){
            System.out.println("No Save File");
            return null;
        }
    }


    //currently unused
    //checks if the save file exists - i.e. has the user made saves before
    public static boolean hasSaveFile(){
        File file = new File(savePath);
        return file.exists();
    }

}

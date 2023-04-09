package com.example.BackEnd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class Store {

    //path to make saves to
    private static final String savePath = "C:/Users/Liam/OneDrive - National University of Ireland, Galway/Coding/demo/src/main/java/com/example/BackEnd/saves/save.txt";
    
    //save a day to the file
    //this basically saves everything as Days implement a linked list type functionality
    public static void save(Day day){
        try{
            FileOutputStream fStream = new FileOutputStream(savePath);
            ObjectOutputStream oStream = new ObjectOutputStream(fStream);
            oStream.writeObject(day);
            oStream.close();
            fStream.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    //read the saved data from the file
    public static Day load(){
        try{
            FileInputStream fStream = new FileInputStream(savePath);
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

    //checks if the save file exists - i.e. has the user made saves before
    public static boolean hasSaveFile(){
        File file = new File(savePath);
        return file.exists();
    }

}

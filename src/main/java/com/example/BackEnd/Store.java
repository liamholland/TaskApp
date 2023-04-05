package com.example.BackEnd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class Store {

    private static final String savePath = "C:/Users/Liam/OneDrive - National University of Ireland, Galway/Coding/demo/src/main/java/com/example/BackEnd/saves/save.txt";
    
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

    public static Day read(){
        try{
            FileInputStream fStream = new FileInputStream(savePath);
            ObjectInputStream oStream = new ObjectInputStream(fStream);
            Day day = (Day)oStream.readObject();
            oStream.close();
            fStream.close();
            return day;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static boolean hasSaveFile(){
        File file = new File(savePath);
        return file.exists();
    }

}

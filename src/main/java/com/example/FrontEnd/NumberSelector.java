package com.example.FrontEnd;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

//custom control for selecting values within a certain range
public class NumberSelector extends VBox {

    private int min;    //min for this counter
    private int max;    //max for this counter
    private int currentNumber;  //current value
    private NumberSelector greaterUnit; //allows for overflow

    @FXML private Button upButton;  //button to increase the number
    @FXML private Button downButton;    //button to decrease the number
    @FXML private Label numberDisplay;  //display of the number

    //constructor
    //min is inclusive, max is exclusive
    public NumberSelector(int min, int max, NumberSelector greater) throws IllegalArgumentException{
        if(min > max){
            throw new IllegalArgumentException("Min cannot be greater than max");
        }
        
        this.min = min;
        this.max = max;
        currentNumber = min;
        greaterUnit = greater;

        //load fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "task.fxml"));
        try {
            fxmlLoader.setLocation(new URL(
                    "file:///C:/Users/Liam/OneDrive - National University of Ireland, Galway/Coding/demo/src/main/resources/com/example/number_selector.fxml"));    //hopefully change this to a relative value at some point
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        //set the initial label value
        numberDisplay.textProperty().set(Integer.toString(this.min));
    }

    //other constructor
    public NumberSelector(int min, int max){
        this(min, max, null);
    }
    
    //update the label with the current value of the counter
    private void updateDisplay(){
        numberDisplay.textProperty().set(Integer.toString(currentNumber));
    }

    //increase the number
    public void up(){
        currentNumber++;
        if(currentNumber >= max){
            currentNumber = min;
            if(greaterUnit != null){
                greaterUnit.up();
            }
        }
        updateDisplay();
    }

    //dcrease the number
    public void down(){
        currentNumber--;
        if(currentNumber < min){
            currentNumber = max - 1;
            if(greaterUnit != null){
                greaterUnit.down();
            }
        }
        updateDisplay();
    }

    //get the current value of the counter
    public int getCurrent(){
        return currentNumber;
    }

    public void setMin(int value){
        if(min < max){
            min = value;
        }
    }

    public void setMax(int value){
        if(max > min){
            max = value;
        }
    }

    public void setGreater(NumberSelector selector){
        greaterUnit = selector;
    }
}

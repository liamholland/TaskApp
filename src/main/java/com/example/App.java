package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.BackEnd.APITest;
import com.example.BackEnd.DayViewer;
import com.example.FrontEnd.PrimaryController;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        DayViewer dayViewer = new DayViewer();

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
        
        scene = new Scene(fxmlLoader.load(), 1280, 800);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

        PrimaryController controller = fxmlLoader.getController();
        controller.setDayViewer(dayViewer);

        controller.displayTasksForDay();
    }

    public static void main(String[] args) {
        launch();
    }

}
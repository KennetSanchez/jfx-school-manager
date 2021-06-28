package ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    ManagerGUI gui;
    public Main(){
        
    }

    public static void main(String [] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        gui = new ManagerGUI();
    }
    
}

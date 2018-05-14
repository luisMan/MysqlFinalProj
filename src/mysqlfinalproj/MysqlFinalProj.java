/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlfinalproj;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author luism
 */
public class MysqlFinalProj extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
        SqlPane sqlpane = new SqlPane(primaryStage,1500,1000);
 
       
        primaryStage.setTitle("MySqlProject by Luis Manon");
        primaryStage.setScene(sqlpane.getPrimaryScene());
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

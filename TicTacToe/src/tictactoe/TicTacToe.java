/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Mahmoud Ism
 */
public class TicTacToe extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("PlayOnlineScreen.fxml"));

       /*  AnchorPane root = new AnchorPane();
        
        root.getChildren().add(ScreenController.loginScreen);
        root.getChildren().add(ScreenController.registerScreen);
        
   
        ScreenController.viewPane(ScreenController.loginScreen);

        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
 */
        Scene scene = new Scene(root);
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import screens.PlayOnlineScreen;
import screens.StartScreenBase;

/**
 *
 * @author Mahmoud Ism
 */
public class TicTacToe extends Application {

    StartScreenBase startScreen;
    Scene startScene;


    @Override
    public void start(Stage primaryStage) throws Exception {
     
        initStartScreen(primaryStage);

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(startScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void initStartScreen(Stage currentStage) {
          
        Font.loadFont(TicTacToe.class.getResource("/assets/fonts/Stroke-082d.ttf").toExternalForm(), 10);

        startScreen = new StartScreenBase(currentStage);
        startScreen.setStyle("-fx-font-family: Stroke;");
        
        startScene = new Scene(startScreen);
        startScene.getStylesheets().add(getClass().getResource("/assets/css.css").toExternalForm());
       
    }  

}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.time.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import screens.LoginScreen;
import screens.RegisterScreen;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import screens.ComputerGameBoardScreen;
import screens.OfflineGameBoardScreen;
import screens.OfflineScreenBase;
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


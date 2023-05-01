/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import screens.LoginScreen;
import screens.RegisterScreen;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import screens.ComputerGameBoardScreen;
import screens.OfflineGameBoardScreen;

/**
 *
 * @author Mahmoud Ism
 */
public class TicTacToe extends Application {
    
    LoginScreen loginScreen;
    RegisterScreen registerScreen;
    OfflineGameBoardScreen offlineGameBoardScreen;
    ComputerGameBoardScreen computerGameBoardScreen;
    Scene loginScene;
    Scene registerScene;
    Scene offlineGameBoardScene;
    Scene computerGameBoardScene;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        initScreens();
        
        initScenes();
        
        initNavButtons(primaryStage);
        
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(computerGameBoardScene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void initScreens() {
        Font.loadFont(TicTacToe.class.getResource("/assets/fonts/Stroke-082d.ttf").toExternalForm(), 10);
        
        loginScreen = new LoginScreen();
        registerScreen = new RegisterScreen();
        offlineGameBoardScreen = new OfflineGameBoardScreen();
        computerGameBoardScreen = new ComputerGameBoardScreen();
        
        loginScreen.setStyle("-fx-font-family: Stroke;");
        registerScreen.setStyle("-fx-font-family: Stroke;");
        offlineGameBoardScreen.setStyle("-fx-font-family: Stroke;");
        computerGameBoardScreen.setStyle("-fx-font-family: Stroke;");
    }

    private void initScenes() {
        loginScene = new Scene(loginScreen);
        registerScene = new Scene(registerScreen);
        offlineGameBoardScene = new Scene(offlineGameBoardScreen);
        computerGameBoardScene = new Scene(computerGameBoardScreen);
    }

    private void initNavButtons(Stage currentStage) {
        loginScreen.txt_signUp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
               currentStage.setScene(registerScene);
            }
        });
        
        registerScreen.txt_signIn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
               currentStage.setScene(loginScene);
            }
        });
    }
    
}

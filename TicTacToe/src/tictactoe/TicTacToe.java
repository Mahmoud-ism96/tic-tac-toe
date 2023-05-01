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
import javafx.stage.Stage;
import screens.GameBoardScreen;

/**
 *
 * @author Mahmoud Ism
 */
public class TicTacToe extends Application {
    
    LoginScreen loginScreen;
    RegisterScreen registerScreen;
    GameBoardScreen gameBoardScreen;
    Scene loginScene;
    Scene registerScene;
    Scene gameBoardScene;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        initScreens();
        
        initScenes();
        
        initNavButtons(primaryStage);

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(gameBoardScene);
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
        loginScreen = new LoginScreen();
        registerScreen = new RegisterScreen();
        gameBoardScreen = new GameBoardScreen();
    }

    private void initScenes() {
        loginScene = new Scene(loginScreen);
        registerScene = new Scene(registerScreen);
        gameBoardScene = new Scene(gameBoardScreen);
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

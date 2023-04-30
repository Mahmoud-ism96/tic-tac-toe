/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import static javafx.scene.paint.Color.BLUE;
import javafx.stage.Stage;

/**
 *
 * @author Mahmoud Ism
 */
public class TicTacToe extends Application {
    
    LoginScreen loginScreen;
    RegisterScreen registerScreen;
    Scene loginScene;
    Scene registerScene;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        initScreens();
        
        initScenes();
        
        initNavButtons(primaryStage);

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(loginScene);
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
    }

    private void initScenes() {
        loginScene = new Scene(loginScreen);
        registerScene= new Scene(registerScreen);

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

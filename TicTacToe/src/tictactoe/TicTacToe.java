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
import screens.GameHistoryScreen;
import screens.OnlinePlayerListScreenBase;
import screens.StartScreenBase;

/**
 *
 * @author Mahmoud Ism
 */
public class TicTacToe extends Application {

    StartScreenBase startScreen;
    Scene startScene;

    private static Stage currentStage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        currentStage = primaryStage;

        initStartScreen(primaryStage);

        primaryStage.setOnCloseRequest(event -> {
            if (ServerConnection.getInstance().getVsPlayerID() != null) {
                ServerConnection.getInstance().playerLeft();
            }
            OnlinePlayerListScreenBase.isRunning = false;
            GameHistoryScreen.isRunning = false;
        });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (ServerConnection.getInstance().getVsPlayerID() != null) {
                ServerConnection.getInstance().playerLeft();
            }
            OnlinePlayerListScreenBase.isRunning = false;
            GameHistoryScreen.isRunning = false;
        }));

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(startScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        ServerConnection.getInstance().closeConnection();
    }

    public static Stage getCurrentStage() {
        return currentStage;
    }

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

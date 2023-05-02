/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.Optional;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
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
    OfflineScreenBase offlineScreen;
    LoginScreen loginScreen;
    RegisterScreen registerScreen;
    OfflineGameBoardScreen offlineGameBoardScreen;
    ComputerGameBoardScreen computerGameBoardScreen;
    Scene startScene;
    Scene offlineScene;
    Scene loginScene;
    Scene registerScene;
    Scene offlineGameBoardScene;
    Scene computerGameBoardScene;
    Dialog warningDialog;
    ButtonType yesButton;
    Optional<ButtonType> result;

    @Override
    public void start(Stage primaryStage) throws Exception {

        initScreens();

        initScenes();

        initNavButtons(primaryStage);

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

    private void initScreens() {
        Font.loadFont(TicTacToe.class.getResource("/assets/fonts/Stroke-082d.ttf").toExternalForm(), 10);

        startScreen = new StartScreenBase();
        offlineScreen = new OfflineScreenBase();
        loginScreen = new LoginScreen();
        registerScreen = new RegisterScreen();
        offlineGameBoardScreen = new OfflineGameBoardScreen();
        computerGameBoardScreen = new ComputerGameBoardScreen();

        startScreen.setStyle("-fx-font-family: Stroke;");
        offlineScreen.setStyle("-fx-font-family: Stroke;");
        loginScreen.setStyle("-fx-font-family: Stroke;");
        registerScreen.setStyle("-fx-font-family: Stroke;");
        offlineGameBoardScreen.setStyle("-fx-font-family: Stroke;");
        computerGameBoardScreen.setStyle("-fx-font-family: Stroke;");
    }

    private void initScenes() {
        startScene = new Scene(startScreen);
        startScene.getStylesheets().add(getClass().getResource("/assets/css.css").toExternalForm());
        offlineScene = new Scene(offlineScreen);
        offlineScene.getStylesheets().add(getClass().getResource("/assets/css.css").toExternalForm());
        loginScene = new Scene(loginScreen);
        registerScene = new Scene(registerScreen);
        offlineGameBoardScene = new Scene(offlineGameBoardScreen);
        computerGameBoardScene = new Scene(computerGameBoardScreen);
    }

    private void initNavButtons(Stage currentStage) {

        startScreen.playOfflineBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                currentStage.setScene(offlineScene);
            }
        });

        startScreen.playOnlineBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                currentStage.setScene(loginScene);
            }
        });

        offlineScreen.playLocallyBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                currentStage.setScene(offlineGameBoardScene);
            }
        });

        offlineScreen.play_with_pc.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                currentStage.setScene(computerGameBoardScene);
            }
        });

        offlineScreen.backImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                currentStage.setScene(startScene);
            }
        });

        loginScreen.txt_signUp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                currentStage.setScene(registerScene);
            }
        });

        loginScreen.icon_back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                currentStage.setScene(startScene);
            }
        });

        registerScreen.txt_signIn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                currentStage.setScene(loginScene);
            }
        });
        registerScreen.icon_back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                currentStage.setScene(startScene);
            }
        });

        offlineGameBoardScreen.btn_back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                leavingAlert();
                if (result.get() == yesButton) {
                    currentStage.setScene(offlineScene);
                    offlineGameBoardScreen.resetScores();
                    offlineGameBoardScreen.playAgain();
                }
            }
        });
        computerGameBoardScreen.btn_back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                leavingAlert();
                if (result.get() == yesButton) {
                    currentStage.setScene(offlineScene);
                    computerGameBoardScreen.resetScores();
                    computerGameBoardScreen.playAgain();
                }
            }
        });

    }

    public void leavingAlert() {

        Dialog warningDialog = new Dialog();
        DialogPane dialogPane = warningDialog.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #fff;");
        warningDialog.setContentText("Are you sure want to leave?");
        warningDialog.setTitle("Confirmation");

        yesButton = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        warningDialog.getDialogPane().getButtonTypes().addAll(yesButton, cancelButton);
        dialogPane.lookupButton(cancelButton).setVisible(true);

        Button okButton = (Button) warningDialog.getDialogPane().lookupButton(yesButton);
        okButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: #fff;");
        okButton.setAlignment(Pos.CENTER);

        result = warningDialog.showAndWait();

    }

}

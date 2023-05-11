/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tictactoe.Navigation;

public class PlayOnlineScreen extends AnchorPane {

    protected final ImageView imageView;
    protected final Button playOnlineBtn;
    protected final Button playOfflineBtn;
    protected final ImageView logoImage;
    protected final Text text;
    protected final Text displayName;
    protected final Button gameHistoryBtn;
    protected final Button logoutBtn;
    protected Stage currentStage;

    public PlayOnlineScreen(Stage primaryStage) {

        imageView = new ImageView();
        playOnlineBtn = new Button();
        playOfflineBtn = new Button();
        logoImage = new ImageView();
        text = new Text();
        displayName = new Text();
        gameHistoryBtn = new Button();
        logoutBtn = new Button();
        currentStage = primaryStage;

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        imageView.setFitHeight(400.0);
        imageView.setFitWidth(600.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("/images/background.png").toExternalForm()));

        AnchorPane.setLeftAnchor(playOnlineBtn, 230.0);
        AnchorPane.setRightAnchor(playOnlineBtn, 230.0);
        playOnlineBtn.setLayoutY(190);
        playOnlineBtn.setMnemonicParsing(false);
        playOnlineBtn.setText("Play Online");
        playOnlineBtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        playOnlineBtn.getStyleClass().add("button");

        AnchorPane.setLeftAnchor(playOfflineBtn, 230.0);
        AnchorPane.setRightAnchor(playOfflineBtn, 230.0);
        playOfflineBtn.setLayoutY(250.0);
        playOfflineBtn.setMnemonicParsing(false);
        playOfflineBtn.setText("Play Offline");
        playOfflineBtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        playOfflineBtn.getStyleClass().add("button");

        text.setText("Welcome");
        text.setLayoutX(250.0 - text.getText().length());
        text.setLayoutY(100.0);
        text.setStyle("-fx-font-size: 30; -fx-font-weight: bold;");

        displayName.setText("Player 1");
        displayName.setLayoutY(130.0);
        final double width = displayName.getLayoutBounds().getWidth();
        displayName.setLayoutX(300.0 - width);
        
        System.err.println(width);
        displayName.setStyle("-fx-font-size: 30; -fx-font-weight: bold;");

        AnchorPane.setBottomAnchor(gameHistoryBtn, 45.0);
        AnchorPane.setLeftAnchor(gameHistoryBtn, 52.0);
        gameHistoryBtn.setLayoutX(52.0);
        gameHistoryBtn.setLayoutY(329.0);
        gameHistoryBtn.setMnemonicParsing(false);
        gameHistoryBtn.setText("Game History");
        gameHistoryBtn.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");

        AnchorPane.setBottomAnchor(logoutBtn, 50.0);
        AnchorPane.setRightAnchor(logoutBtn, 50.0);
        logoutBtn.setLayoutX(448.0);
        logoutBtn.setLayoutY(323.0);
        logoutBtn.setMnemonicParsing(false);
        logoutBtn.setPrefHeight(26.0);
        logoutBtn.setPrefWidth(85.0);
        logoutBtn.setText("Logout");
        logoutBtn.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
        getChildren().add(imageView);
        getChildren().add(playOnlineBtn);
        getChildren().add(playOfflineBtn);
        getChildren().add(logoImage);
        getChildren().add(text);
        getChildren().add(displayName);
        getChildren().add(gameHistoryBtn);
        getChildren().add(logoutBtn);

        initButtonActions();
    }

    public void initButtonActions() {
        logoutBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                StartScreenBase startScreen = new StartScreenBase(currentStage);
                Navigation.getInstance().navigate(startScreen, currentStage);
            }
        });
        
        playOfflineBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                OfflineScreenBase offlineScreen = new OfflineScreenBase(currentStage);
                Navigation.getInstance().navigate(offlineScreen, currentStage);
            }
        });
        
        playOnlineBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                OnlinePlayerListScreenBase OnlinePlayerListScreen = new OnlinePlayerListScreenBase(currentStage);
                Navigation.getInstance().navigate(OnlinePlayerListScreen, currentStage);
            }
        });
    }

}
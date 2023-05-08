package screens;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import tictactoe.ServerConnection;

public class OfflineScreenBase extends AnchorPane {

    protected final ImageView imageView;
    public final Button playLocallyBtn;
    public final Button play_with_pc;
    protected final ImageView logoImage;
    protected final Text text;
    public final ImageView backImage;

    protected Stage currentStage;

    public OfflineScreenBase(Stage primaryStage) {

        currentStage = primaryStage;

        imageView = new ImageView();
        playLocallyBtn = new Button();
        play_with_pc = new Button();
        logoImage = new ImageView();
        text = new Text();
        backImage = new ImageView();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        imageView.setFitHeight(400.0);
        imageView.setFitWidth(600.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("/images/background.png").toExternalForm()));

        AnchorPane.setLeftAnchor(playLocallyBtn, 230.0);
        AnchorPane.setRightAnchor(playLocallyBtn, 230.0);
        playLocallyBtn.setLayoutY(190);
        playLocallyBtn.setMnemonicParsing(false);
        playLocallyBtn.setText("Play locally");
        playLocallyBtn.setTextAlignment(TextAlignment.CENTER);
        play_with_pc.getStyleClass().add("button");

        AnchorPane.setLeftAnchor(play_with_pc, 193.0);
        AnchorPane.setRightAnchor(play_with_pc, 193.0);
        play_with_pc.setLayoutY(250);
        play_with_pc.setMnemonicParsing(false);
        play_with_pc.setTextAlignment(TextAlignment.CENTER);
        play_with_pc.setText("Play With Compluter");
        play_with_pc.setId("allbtn");
        play_with_pc.getStyleClass().add("button");

        logoImage.setLayoutX(120.0);
        logoImage.setLayoutY(75.0);
        logoImage.setFitHeight(70.0);
        logoImage.setFitWidth(100.0);

        logoImage.setPickOnBounds(true);
        logoImage.setPreserveRatio(true);
        logoImage.setImage(new Image(getClass().getResource("/images/logo.png").toExternalForm()));

        text.setLayoutX(200.0);
        text.setLayoutY(125.0);
        text.setText("Tic Tac Toe");
        text.setStyle("-fx-font-size: 50; -fx-font-weight: bold;");

        backImage.setFitHeight(50.0);
        backImage.setFitWidth(50.0);
        backImage.setLayoutX(25.0);
        backImage.setLayoutY(15.0);
        backImage.setPickOnBounds(true);
        backImage.setPreserveRatio(true);
        backImage.setImage(new Image(getClass().getResource("/images/back_button.png").toExternalForm()));

        getChildren().add(imageView);
        getChildren().add(playLocallyBtn);
        getChildren().add(play_with_pc);
        getChildren().add(logoImage);
        getChildren().add(text);
        getChildren().add(backImage);

        initButtonActions();

    }

    public void initButtonActions() {
        playLocallyBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                OfflineGameBoardScreen offlineGameBoardScreen = new OfflineGameBoardScreen(currentStage);
                navigate(offlineGameBoardScreen);
            }
        });

        play_with_pc.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ComputerGameBoardScreen computerGameBoardScreen = new ComputerGameBoardScreen(currentStage);
                navigate(computerGameBoardScreen);
            }
        });

        backImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Parent returnToScreen;
                if (ServerConnection.getConnectionState()) {
                    returnToScreen = new PlayOnlineScreen(currentStage);
                } else {
                    returnToScreen = new StartScreenBase(currentStage);
                }
                
                navigate(returnToScreen);

            }
        });
    }

    public void navigate(Parent screen) {
        screen.setStyle("-fx-font-family: Stroke;");
        Scene scene = new Scene(screen);
        scene.getStylesheets().add(getClass().getResource("/assets/css.css").toExternalForm());
        currentStage.setScene(scene);
    }

}

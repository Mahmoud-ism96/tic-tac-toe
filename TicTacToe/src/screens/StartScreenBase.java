package screens;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class StartScreenBase extends AnchorPane {

    protected final ImageView imageView;
    public final Button playOnlineBtn;
    public final Button playOfflineBtn;
    protected final ImageView logoImage;
    protected final Text text;
    
    public StartScreenBase() {
        imageView = new ImageView();
        playOnlineBtn = new Button();
        playOfflineBtn = new Button();
        logoImage = new ImageView();
        text = new Text();

        imageView.setFitHeight(400.0);
        imageView.setFitWidth(600.0);
        imageView.setImage(new Image(getClass().getResource("/images/background.png").toExternalForm()));
        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        AnchorPane.setLeftAnchor(playOnlineBtn, 220.0);
        AnchorPane.setRightAnchor(playOnlineBtn, 220.0);
        playOnlineBtn.setLayoutY(190);
        playOnlineBtn.setMnemonicParsing(false);
        playOnlineBtn.setText("Play Online");
        playOnlineBtn.setTextAlignment(TextAlignment.CENTER);
        playOnlineBtn.getStyleClass().add("button");

        AnchorPane.setLeftAnchor(playOfflineBtn, 220.0);
        AnchorPane.setRightAnchor(playOfflineBtn, 220.0);
        playOfflineBtn.setLayoutY(250);
        playOfflineBtn.setMnemonicParsing(false);
        playOfflineBtn.setTextAlignment(TextAlignment.CENTER);
        playOfflineBtn.setText("Play Offline");
        playOfflineBtn.getStyleClass().add("button");

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

        getChildren().add(imageView);
        getChildren().add(playOnlineBtn);
        getChildren().add(playOfflineBtn);
        getChildren().add(logoImage);
        getChildren().add(text);
    }
}

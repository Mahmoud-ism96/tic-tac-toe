package screens;

import screens.OfflineScreenBase;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public  class StartScreenBase extends AnchorPane {

    protected final ImageView imageView;
    protected final Button playOnlineBtn;
    protected final Button playOfflineBtn;
    protected final ImageView logoImage;
    protected final Text text;
    protected final ImageView backImage;
    
    public StartScreenBase() {
        imageView = new ImageView();
        playOnlineBtn = new Button();
        playOfflineBtn = new Button();
        logoImage = new ImageView();
        text = new Text();
        backImage = new ImageView();
    setBackground(new Background(new BackgroundImage(
    new Image(getClass().getResource("/images/background.jpg").toExternalForm()),
    BackgroundRepeat.NO_REPEAT,
    BackgroundRepeat.NO_REPEAT,
    BackgroundPosition.CENTER,
    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
)));
        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);
 
        AnchorPane.setLeftAnchor(playOnlineBtn, 216.0);
        AnchorPane.setRightAnchor(playOnlineBtn, 216.0);
        playOnlineBtn.setLayoutX(216.0);
        playOnlineBtn.setLayoutY(166.0);
        playOnlineBtn.setMnemonicParsing(false);
        playOnlineBtn.setPrefHeight(43.0);
        playOnlineBtn.setPrefWidth(168.0);
        playOnlineBtn.getStyleClass().add("OnlineButtons");
        playOnlineBtn.getStylesheets().add("/fxml_Screens/../css.css");
        playOnlineBtn.setText("Play Online");
        playOnlineBtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        playOnlineBtn.setFont(new Font("Forte", 24.0));
        playOnlineBtn.setStyle("-fx-background-color: black;-fx-background-radius: 30;");
        playOnlineBtn.setTextFill(Color.WHITE);
        
        AnchorPane.setLeftAnchor(playOfflineBtn, 216.0);
        AnchorPane.setRightAnchor(playOfflineBtn, 216.0);
        playOfflineBtn.setLayoutX(216.0);
        playOfflineBtn.setLayoutY(238.0);
        playOfflineBtn.setMnemonicParsing(false);
        playOfflineBtn.setPrefHeight(43.0);
        playOfflineBtn.setPrefWidth(168.0);
        playOfflineBtn.getStyleClass().add("OnlineButtons");
        playOfflineBtn.getStylesheets().add("/fxml_Screens/../css.css");
        playOfflineBtn.setText("Play Offline");
        playOfflineBtn.setFont(new Font("Forte", 24.0));
        playOfflineBtn.setStyle("-fx-background-color: black;-fx-background-radius: 30;");
        playOfflineBtn.setTextFill(Color.WHITE);

        AnchorPane.setLeftAnchor(logoImage, 161.0);
        AnchorPane.setRightAnchor(logoImage, 378.0);
        AnchorPane.setTopAnchor(logoImage, 64.0);
        logoImage.setFitHeight(61.0);
        logoImage.setFitWidth(93.0);
        logoImage.setLayoutX(161.0);
        logoImage.setLayoutY(64.0);
        logoImage.setPickOnBounds(true);
        logoImage.setPreserveRatio(true);
        logoImage.setImage(new Image(getClass().getResource("/images/logo.png").toExternalForm()));

        text.setLayoutX(246.0);
        text.setLayoutY(106.0);
        text.setStroke(javafx.scene.paint.Color.BLACK);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("Tic Tac Toe");
        text.setFont(new Font("Forte", 36.0));

        backImage.setFitHeight(26.0);
        backImage.setFitWidth(25.0);
        backImage.setLayoutX(22.0);
        backImage.setLayoutY(13.0);
        backImage.setPickOnBounds(true);
        backImage.setPreserveRatio(true);
        backImage.setImage(new Image(getClass().getResource("/images/back.png").toExternalForm()));

        getChildren().add(imageView);
        getChildren().add(playOnlineBtn);
        getChildren().add(playOfflineBtn);
        getChildren().add(logoImage);
        getChildren().add(text);
        getChildren().add(backImage);

        
    playOfflineBtn.setOnAction(e -> {
    OfflineScreenBase offlineScreen = new OfflineScreenBase();
    Scene offlineScene = new Scene(offlineScreen);
    Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
    window.setScene(offlineScene);
    window.show();
});
    
    playOnlineBtn.setOnAction(e -> {
    OfflineScreenBase offlineScreen = new OfflineScreenBase();
    Scene offlineScene = new Scene(offlineScreen);
    Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
    window.setScene(offlineScene);
    window.show();
});
        
    }
    
}


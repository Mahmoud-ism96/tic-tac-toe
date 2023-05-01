package screens;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public  class OfflineScreenBase extends AnchorPane {

    protected final ImageView imageView;
    protected final Button playLocallyBtn;
    protected final Button play_with_pc;
    protected final ImageView logoImage;
    protected final Text text;
    protected final ImageView backImage;

    public OfflineScreenBase() {

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
        imageView.setImage(new Image(getClass().getResource("/images/background.jpg").toExternalForm()));

        AnchorPane.setLeftAnchor(playLocallyBtn, 204.0);
        AnchorPane.setRightAnchor(playLocallyBtn, 216.0);
        playLocallyBtn.setId("play_locally");
        playLocallyBtn.setLayoutX(204.0);
        playLocallyBtn.setLayoutY(166.0);
        playLocallyBtn.setMnemonicParsing(false);
        playLocallyBtn.setPrefHeight(43.0);
        playLocallyBtn.setPrefWidth(180.0);
        playLocallyBtn.getStyleClass().add("OnlineButtons");
        playLocallyBtn.getStylesheets().add("/fxml_Screens/../css.css");
        playLocallyBtn.setText("Play locally");
        playLocallyBtn.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        playLocallyBtn.setFont(new Font("Forte", 24.0));
        playLocallyBtn.setStyle("-fx-background-color: black;-fx-background-radius: 30;");
        playLocallyBtn.setTextFill(Color.WHITE);

        AnchorPane.setLeftAnchor(play_with_pc, 165.0);
        AnchorPane.setRightAnchor(play_with_pc, 176.0);
        play_with_pc.setLayoutX(165.0);
        play_with_pc.setLayoutY(238.0);
        play_with_pc.setMnemonicParsing(false);
        play_with_pc.setPrefHeight(43.0);
        play_with_pc.setPrefWidth(259.0);
        play_with_pc.getStyleClass().add("OnlineButtons");
        play_with_pc.getStylesheets().add("/fxml_Screens/../css.css");
        play_with_pc.setText("Play With Compluter");
        play_with_pc.setFont(new Font("Forte", 24.0));
        play_with_pc.setStyle("-fx-background-color: black;-fx-background-radius: 30;");
        play_with_pc.setTextFill(Color.WHITE);

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
        getChildren().add(playLocallyBtn);
        getChildren().add(play_with_pc);
        getChildren().add(logoImage);
        getChildren().add(text);
        getChildren().add(backImage);
    
    backImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
  
    @Override
    public void handle(MouseEvent event) {
    StartScreenBase starteScreen = new StartScreenBase();
    Scene offlineScene = new Scene(starteScreen);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(offlineScene);
    window.show();
        }
          
    });

    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tictactoe.Navigation;

/**
 *
 * @author fg
 */
public class GameHistoryScreen extends AnchorPane {

    private ObservableList<Game> gameHistory;
    private ListView<Game> listView;

    public final ImageView icon_back;
    protected Image img_back;
    protected final ImageView logoImage;
    protected final Text text;

    public GameHistoryScreen(Stage currentStage) {
        gameHistory = FXCollections.observableArrayList(
            new Game("mazen", "mahmoud", LocalDate.of(2022, 5, 1)),
            new Game("shrouk", "bassant", LocalDate.of(2022, 5, 3)),
            new Game("mazen", "mahmoud", LocalDate.of(2022, 5, 1)),
            new Game("shrouk", "bassant", LocalDate.of(2022, 5, 3)),
            new Game("mazen", "mahmoud", LocalDate.of(2022, 5, 1)),
            new Game("shrouk", "bassant", LocalDate.of(2022, 5, 3)),
            new Game("mazen", "mahmoud", LocalDate.of(2022, 5, 1)),
            new Game("shrouk", "bassant", LocalDate.of(2022, 5, 3)),
            new Game("mazen", "mahmoud", LocalDate.of(2022, 5, 1)),
            new Game("shrouk", "bassant", LocalDate.of(2022, 5, 3))
        );

        listView = new ListView<>();
        listView.setPrefWidth(500);
        listView.setPrefHeight(300);
        listView.setItems(gameHistory);
        listView.setCellFactory(param -> new ListCell<Game>() {
      @Override
      protected void updateItem(Game game, boolean empty) {
        super.updateItem(game, empty);
        if (empty || game == null) {
            setText(null);
      } else {
            
        setText(game.getPlayer1() + " vs " + game.getPlayer2() + " - Date: " + game.getDate());
        Button watchButton = new Button("Watch Game");
        
        watchButton.setOnAction(event -> {

        });
        setGraphic(watchButton);
        }
    }
});
        
        Image backgroundImage = new Image("/images/background.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.fitWidthProperty().bind(this.widthProperty());
        backgroundImageView.fitHeightProperty().bind(this.heightProperty());
        AnchorPane anchorPane = new AnchorPane(backgroundImageView);

        AnchorPane.setTopAnchor(listView, 100.0);
        AnchorPane.setLeftAnchor(listView, 50.0);
        AnchorPane.setRightAnchor(listView, 50.0);
        AnchorPane.setBottomAnchor(listView, 50.0);
        anchorPane.getChildren().add(listView);

        logoImage = new ImageView();
        text = new Text();
        logoImage.setLayoutX(120.0);
        logoImage.setLayoutY(15.0);
        logoImage.setFitHeight(70.0);
        logoImage.setFitWidth(100.0);
        logoImage.setPickOnBounds(true);
        logoImage.setPreserveRatio(true);
        logoImage.setImage(new Image(getClass().getResource("/images/logo.png").toExternalForm()));

        text.setLayoutX(200.0);
        text.setLayoutY(60.0);
        text.setText("Game History");
        text.setStyle("-fx-font-size: 40; -fx-font-weight: bold;");

        anchorPane.getChildren().add(logoImage);
        anchorPane.getChildren().add(text);

        icon_back = new ImageView();
        img_back = new Image(getClass().getResourceAsStream("/images/back_button.png"));
        icon_back.setImage(img_back);
        icon_back.setFitHeight(50.0);
        icon_back.setFitWidth(50.0);
        icon_back.setLayoutX(25.0);
        icon_back.setLayoutY(15.0);
        icon_back.setPickOnBounds(true);
        icon_back.setPreserveRatio(true);
        anchorPane.getChildren().add(icon_back);

        setPrefWidth(600);
        setPrefHeight(400);

        getChildren().add(anchorPane);
        
    icon_back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent event) {

        PlayOnlineScreen onlineListScreen = new PlayOnlineScreen(currentStage);
        Navigation.getInstance().navigate(onlineListScreen, currentStage);
            }
               
        });
    }
    
//    public ObservableList<String> getGameHistory() {
//        return gameHistory;
//    }
//
//    public ListView<String> getListView() {
//        return listView;
//    }
    
}


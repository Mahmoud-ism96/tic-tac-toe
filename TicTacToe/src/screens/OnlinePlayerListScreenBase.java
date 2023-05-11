package screens;

import com.google.gson.JsonObject;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import tictactoe.Navigation;
import tictactoe.PlayerData;
import tictactoe.ServerConnection;

public class OnlinePlayerListScreenBase extends AnchorPane {

    private ObservableList<PlayerData> players;
    private ListView<PlayerData> listView;

    public final ImageView icon_back;
    protected Image img_back;
    protected final ImageView logoImage;
    protected final Text text;

    protected Stage currentStage;

    OnlinePlayerListScreenBase(Stage primaryStage) {

        currentStage = primaryStage;

        players = FXCollections.observableArrayList();

        // Create a ListView with a custom cell factory
        listView = new ListView<>(players);
        listView.setPrefWidth(400);
        listView.setCellFactory(new Callback<ListView<PlayerData>, ListCell<PlayerData>>() {
            @Override
            public ListCell<PlayerData> call(ListView<PlayerData> listView) {
                return new ListCell<PlayerData>() {
                    @Override
                    protected void updateItem(PlayerData player, boolean empty) {
                        super.updateItem(player, empty);
                        if (player != null) {
                            // Create an HBox to hold the Label and Button objects
                            HBox hbox = new HBox();
                            hbox.setStyle("-fx-background-color: #ECEBE4;"); // set background color to beige
                            Label nameLabel = new Label(player.getDisplayName());
                            Button button = new Button("Request");
                            nameLabel.setPrefWidth(100);
                            hbox.getChildren().addAll(nameLabel, button);
                            hbox.setAlignment(Pos.CENTER_LEFT); // set alignment to left
                            hbox.setSpacing(260); // set spacing between Label and Button
                            setGraphic(hbox);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });

        // Create a ScrollPane and add the ListView to it
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(listView);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Bind the prefWidth and prefHeight properties of the ScrollPane to the corresponding properties of the ListView
        scrollPane.prefWidthProperty().bind(listView.widthProperty());
        scrollPane.prefHeightProperty().bind(listView.heightProperty());

        // Create an AnchorPane with a background image
        Image backgroundImage = new Image("/images/background.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.fitWidthProperty().bind(this.widthProperty());
        backgroundImageView.fitHeightProperty().bind(this.heightProperty());
        AnchorPane anchorPane = new AnchorPane(backgroundImageView);

        // Add the ScrollPane to the AnchorPane
        AnchorPane.setTopAnchor(scrollPane, 100.0);
        AnchorPane.setLeftAnchor(scrollPane, 50.0);
        AnchorPane.setRightAnchor(scrollPane, 50.0);
        AnchorPane.setBottomAnchor(scrollPane, 50.0);
        anchorPane.getChildren().add(scrollPane);

        // Set the title of the AnchorPane
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
        text.setText("Let's Play Online");
        text.setStyle("-fx-font-size: 40; -fx-font-weight: bold;");

        anchorPane.getChildren().add(logoImage);
        anchorPane.getChildren().add(text);

        // Add a button to the top left of the AnchorPane
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

        // Set the preferred size of the AnchorPane
        setPrefWidth(600);
        setPrefHeight(400);

        // Add the AnchorPane to the VBox
        getChildren().add(anchorPane);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (ServerConnection.getConnectionState()) {
                        ServerConnection.getInstance().parsePlayerList();
                        updateListView(ServerConnection.getInstance().getAllPlayers());
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void initButtonActions() {
        icon_back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                PlayOnlineScreen PlayOnlineScreen = new PlayOnlineScreen(currentStage);
                Navigation.getInstance().navigate(PlayOnlineScreen, currentStage);
            }
        });
    }

    public ObservableList<PlayerData> getPlayers() {
        return players;
    }

    public ListView<PlayerData> getListView() {
        return listView;
    }

    public void updateListView(List<PlayerData> allPlayers) {
        // Clear the current list of players
        if (players != null) {
            players.clear();
        }

        // Iterate through the new list of players and add them to the ObservableList
        if (allPlayers != null) {
            for (PlayerData playerData : allPlayers) {
                PlayerData player = new PlayerData(playerData.getDisplayName());
                players.add(player);
            }
        }

        // Refresh the ListView to update it with the new data
        listView.refresh();
    }

}

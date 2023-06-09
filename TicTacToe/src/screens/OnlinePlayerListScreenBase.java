package screens;

import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import tictactoe.Navigation;
import tictactoe.Player;
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

    public static boolean isRunning;

    protected Thread refreshThread;

    OnlinePlayerListScreenBase(Stage primaryStage) {

        currentStage = primaryStage;

        players = FXCollections.observableArrayList();

        isRunning = true;

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
                            HBox hbox = new HBox();
                            hbox.setStyle("-fx-background-color: #ECEBE4;");
                            Label nameLabel = new Label(player.getDisplay_name());
                            nameLabel.setPrefWidth(100);
                            HBox.setHgrow(nameLabel, Priority.ALWAYS);
                            Button button = new Button("Request");
                            button.setStyle("-fx-font-size: 10px;");
                            hbox.getChildren().addAll(nameLabel, button);
                            hbox.setAlignment(Pos.CENTER_LEFT);
                            hbox.setSpacing(280);
                            hbox.setPadding(new Insets(5, 10, 5, 10));
                            setGraphic(hbox);

                            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    ServerConnection.getInstance().parseGameRequest(Player.getInstance().getUserName(), player.getUser_id());
                                    ServerConnection.getInstance().setPlayer1Name(Player.getInstance().getUserName());
                                    ServerConnection.getInstance().setPlayer2Name(player.getDisplay_name());
                                }
                            });
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(listView);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        scrollPane.prefWidthProperty().bind(listView.widthProperty());
        scrollPane.prefHeightProperty().bind(listView.heightProperty());

        Image backgroundImage = new Image("/images/background.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.fitWidthProperty().bind(this.widthProperty());
        backgroundImageView.fitHeightProperty().bind(this.heightProperty());
        AnchorPane anchorPane = new AnchorPane(backgroundImageView);

        AnchorPane.setTopAnchor(scrollPane, 100.0);
        AnchorPane.setLeftAnchor(scrollPane, 50.0);
        AnchorPane.setRightAnchor(scrollPane, 50.0);
        AnchorPane.setBottomAnchor(scrollPane, 50.0);
        anchorPane.getChildren().add(scrollPane);

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

        refreshThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isRunning) {
                        if (ServerConnection.getConnectionState()) {
                            ServerConnection.getInstance().parsePlayerList();
                            updateListView(ServerConnection.getInstance().getAllPlayers());
                        }
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        refreshThread.start();

        initButtonActions();
    }

    public void initButtonActions() {
        icon_back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                isRunning = false;
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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (players != null) {
                    players.clear();
                }

                if (allPlayers != null) {
                    for (PlayerData playerData : allPlayers) {
                        PlayerData player = new PlayerData(playerData.getDisplay_name(), playerData.getUser_id());
                        players.add(player);
                    }
                }

                listView.refresh();
            }
        });

    }

}

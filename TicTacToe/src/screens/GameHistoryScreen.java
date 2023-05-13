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
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import tictactoe.Game;
import tictactoe.Navigation;
import tictactoe.Player;
import tictactoe.ServerConnection;

public class GameHistoryScreen extends AnchorPane {

    private ObservableList<Game> games;
    private ListView<Game> gameListView;

    public final ImageView icon_back;
    protected Image img_back;
    protected final ImageView logoImage;
    protected final Text text;

    protected Stage currentStage;

    public static boolean isRunning;

    protected Thread refreshThread;

    GameHistoryScreen(Stage primaryStage) {

        currentStage = primaryStage;

        games = FXCollections.observableArrayList();

        isRunning = true;

        gameListView = new ListView<>(games);
        gameListView.setPrefWidth(400);
        gameListView.setCellFactory(new Callback<ListView<Game>, ListCell<Game>>() {
            @Override
            public ListCell<Game> call(ListView<Game> listView) {
                return new ListCell<Game>() {
                    @Override
                    protected void updateItem(Game game, boolean empty) {
                        super.updateItem(game, empty);
                        if (game != null) {
                            HBox hbox = new HBox();
                            hbox.setStyle("-fx-background-color: #ECEBE4;");

                            Label winnerLabel = new Label(game.getWINNER_NAME());
                            winnerLabel.setTextFill(Color.GREEN);

                            Label vsLabel = new Label(" vs ");

                            Label loserLabel = new Label(game.getLOSER_NAME());
                            loserLabel.setTextFill(Color.RED);

                            Label dateLabel = new Label(game.getGAME_DATE().toString());

                            Button viewButton = new Button("Replay");
                            viewButton.setStyle("-fx-font-size: 10px;");

                            hbox.getChildren().addAll(winnerLabel, vsLabel, loserLabel, new Region(), dateLabel, new Region(), viewButton);
                            hbox.setAlignment(Pos.CENTER_LEFT);
                            hbox.setSpacing(10);
                            HBox.setHgrow(hbox.getChildren().get(3), Priority.ALWAYS);
                            HBox.setHgrow(hbox.getChildren().get(5), Priority.ALWAYS);
                            hbox.setPadding(new Insets(5, 10, 5, 10));

                            setGraphic(hbox);

                            viewButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    ReplyGameScreen replyGameScreen = new ReplyGameScreen(currentStage, game.getWINNER_NAME(), game.getLOSER_NAME(), game.getGAME_MOVES());
                                    Navigation.getInstance().navigate(replyGameScreen, currentStage);
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
        scrollPane.setContent(gameListView);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        scrollPane.prefWidthProperty().bind(gameListView.widthProperty());
        scrollPane.prefHeightProperty().bind(gameListView.heightProperty());

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

        refreshThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isRunning) {
                        if (ServerConnection.getConnectionState()) {
                            ServerConnection.getInstance().getGameHistory();
                            updateListView(ServerConnection.getInstance().getAllGames());
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

    public ObservableList<Game> getGames() {
        return games;
    }

    public ListView<Game> getGameListView() {
        return gameListView;
    }

    public void updateListView(List<Game> allGames) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (games != null) {
                    games.clear();
                }

                if (allGames != null) {
                    for (Game game : allGames) {
                        games.add(game);
                    }
                }

                gameListView.refresh();
            }
        });

    }

}

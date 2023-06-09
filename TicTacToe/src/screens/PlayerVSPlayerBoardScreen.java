/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import java.util.Optional;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tictactoe.Navigation;
import tictactoe.Player;
import tictactoe.ServerConnection;
import tictactoe.TicTacToe;

/**
 *
 * @author Mahmoud Ism
 */
public class PlayerVSPlayerBoardScreen extends AnchorPane {

    protected ImageView win_condition;
    protected final ImageView imageView;
    protected final Text text_player_one;
    public final ImageView btn_back;
    protected final Text text_player_two;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final ImageView board_0_0;
    protected final ImageView board_0_1;
    protected final ImageView board_0_2;
    protected final ImageView board_1_0;
    protected final ImageView board_1_1;
    protected final ImageView board_1_2;
    protected final ImageView board_2_0;
    protected final ImageView board_2_1;
    protected final ImageView board_2_2;
    protected final ImageView imageView0;
    protected final ImageView imageView1;
    protected final ImageView imageView2;
    protected final ImageView imageView3;
    protected int currentTurn;
    protected int[][] gameBoardX;
    protected int[][] gameBoardO;
    protected boolean isDraw;
    protected boolean noWinner;
    protected final DisplayingVideo displayingVideo;
    protected Stage currentStage;

    protected Dialog warningDialog;
    protected ButtonType yesButton;
    protected Optional<ButtonType> result;

    protected String playerOneName;
    protected String playerTwoName;
    protected int startingTurn;

    protected ImageView[] buttonList;

    private String gameMoves;

    public PlayerVSPlayerBoardScreen(Stage primaryStage, int turn) {

        currentStage = primaryStage;

        win_condition = new ImageView();
        imageView = new ImageView();
        text_player_one = new Text();
        btn_back = new ImageView();
        text_player_two = new Text();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        board_0_0 = new ImageView();
        board_0_1 = new ImageView();
        board_0_2 = new ImageView();
        board_1_0 = new ImageView();
        board_1_1 = new ImageView();
        board_1_2 = new ImageView();
        board_2_0 = new ImageView();
        board_2_1 = new ImageView();
        board_2_2 = new ImageView();
        imageView0 = new ImageView();
        imageView1 = new ImageView();
        imageView2 = new ImageView();
        imageView3 = new ImageView();
        gameBoardX = new int[3][3];
        gameBoardO = new int[3][3];
        displayingVideo = new DisplayingVideo();
        isDraw = true;
        noWinner = true;

        buttonList = new ImageView[9];

        currentTurn = 1;
        startingTurn = turn;
        gameMoves = "";

        buttonList[0] = board_0_0;
        buttonList[1] = board_0_1;
        buttonList[2] = board_0_2;
        buttonList[3] = board_1_0;
        buttonList[4] = board_1_1;
        buttonList[5] = board_1_2;
        buttonList[6] = board_2_0;
        buttonList[7] = board_2_1;
        buttonList[8] = board_2_2;

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        imageView.setFitHeight(400.0);
        imageView.setFitWidth(600.0);
        imageView.setImage(new Image(getClass().getResource("/images/background.png").toExternalForm()));

        playerOneName = ServerConnection.getInstance().getPlayer1Name();
        playerTwoName = ServerConnection.getInstance().getPlayer2Name();

        AnchorPane.setLeftAnchor(text_player_one, 15.0);
        AnchorPane.setTopAnchor(text_player_one, 15.0);
        text_player_one.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text_player_one.setStrokeWidth(0.0);
        text_player_one.setText(playerOneName);
        text_player_one.setStyle("-fx-font-size: 20;");

        AnchorPane.setBottomAnchor(btn_back, 20.0);
        AnchorPane.setLeftAnchor(btn_back, 20.0);
        btn_back.setFitHeight(50.0);
        btn_back.setFitWidth(50.0);
        btn_back.setLayoutX(25.0);
        btn_back.setLayoutY(15.0);
        btn_back.setPickOnBounds(true);
        btn_back.setPreserveRatio(true);
        btn_back.setImage(new Image(getClass().getResource("/images/back_button.png").toExternalForm()));

        AnchorPane.setRightAnchor(text_player_two, 15.0);
        AnchorPane.setTopAnchor(text_player_two, 15.0);
        text_player_two.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text_player_two.setStrokeWidth(0.0);
        text_player_two.setText(playerTwoName);
        text_player_two.setStyle("-fx-font-size: 20;");

        gridPane.setLayoutX(100.0);
        gridPane.setMaxHeight(USE_PREF_SIZE);
        gridPane.setMaxWidth(USE_PREF_SIZE);
        gridPane.setPrefHeight(400.0);
        gridPane.setPrefWidth(400.0);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(100.0);

        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(100.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(30.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(30.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        GridPane.setHalignment(board_0_0, javafx.geometry.HPos.RIGHT);
        GridPane.setValignment(board_0_0, javafx.geometry.VPos.BOTTOM);
        GridPane.setColumnIndex(board_0_0, 0);
        GridPane.setRowIndex(board_0_0, 0);
        board_0_0.setFitHeight(90.0);
        board_0_0.setFitWidth(90.0);
        board_0_0.setPickOnBounds(true);
        board_0_0.setPreserveRatio(true);
        GridPane.setMargin(board_0_0, new Insets(0.0, 15.0, 15.0, 0.0));

        GridPane.setColumnIndex(board_0_1, 1);
        GridPane.setRowIndex(board_0_1, 0);
        GridPane.setHalignment(board_0_1, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(board_0_1, javafx.geometry.VPos.BOTTOM);
        board_0_1.setFitHeight(90.0);
        board_0_1.setFitWidth(90.0);
        board_0_1.setPickOnBounds(true);
        board_0_1.setPreserveRatio(true);
        GridPane.setMargin(board_0_1, new Insets(0.0, 0.0, 15.0, 0.0));

        GridPane.setColumnIndex(board_0_2, 2);
        GridPane.setRowIndex(board_0_2, 0);
        GridPane.setHalignment(board_0_2, javafx.geometry.HPos.LEFT);
        GridPane.setValignment(board_0_2, javafx.geometry.VPos.BOTTOM);
        board_0_2.setFitHeight(90.0);
        board_0_2.setFitWidth(90.0);
        board_0_2.setPickOnBounds(true);
        board_0_2.setPreserveRatio(true);
        GridPane.setMargin(board_0_2, new Insets(0.0, 0.0, 15.0, 15.0));

        GridPane.setHalignment(board_1_0, javafx.geometry.HPos.RIGHT);
        GridPane.setRowIndex(board_1_0, 1);
        GridPane.setColumnIndex(board_1_0, 0);
        GridPane.setValignment(board_1_0, javafx.geometry.VPos.CENTER);
        board_1_0.setFitHeight(90.0);
        board_1_0.setFitWidth(90.0);
        board_1_0.setPickOnBounds(true);
        board_1_0.setPreserveRatio(true);
        GridPane.setMargin(board_1_0, new Insets(0.0, 15.0, 0.0, 0.0));

        GridPane.setColumnIndex(board_1_1, 1);
        GridPane.setHalignment(board_1_1, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(board_1_1, 1);
        GridPane.setValignment(board_1_1, javafx.geometry.VPos.CENTER);
        board_1_1.setFitHeight(90.0);
        board_1_1.setFitWidth(90.0);
        board_1_1.setPickOnBounds(true);
        board_1_1.setPreserveRatio(true);

        GridPane.setColumnIndex(board_1_2, 2);
        GridPane.setHalignment(board_1_2, javafx.geometry.HPos.LEFT);
        GridPane.setRowIndex(board_1_2, 1);
        GridPane.setValignment(board_1_2, javafx.geometry.VPos.CENTER);
        board_1_2.setFitHeight(90.0);
        board_1_2.setFitWidth(90.0);
        board_1_2.setPickOnBounds(true);
        board_1_2.setPreserveRatio(true);
        GridPane.setMargin(board_1_2, new Insets(0.0, 0.0, 0.0, 15.0));

        GridPane.setHalignment(board_2_0, javafx.geometry.HPos.RIGHT);
        GridPane.setRowIndex(board_2_0, 2);
        GridPane.setColumnIndex(board_2_0, 0);
        GridPane.setValignment(board_2_0, javafx.geometry.VPos.TOP);
        board_2_0.setFitHeight(90.0);
        board_2_0.setFitWidth(90.0);
        board_2_0.setPickOnBounds(true);
        board_2_0.setPreserveRatio(true);
        GridPane.setMargin(board_2_0, new Insets(15.0, 15.0, 0.0, 0.0));

        GridPane.setColumnIndex(board_2_1, 1);
        GridPane.setHalignment(board_2_1, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(board_2_1, 2);
        GridPane.setValignment(board_2_1, javafx.geometry.VPos.TOP);
        board_2_1.setFitHeight(90.0);
        board_2_1.setFitWidth(90.0);
        board_2_1.setPickOnBounds(true);
        board_2_1.setPreserveRatio(true);
        GridPane.setMargin(board_2_1, new Insets(15.0, 0.0, 0.0, 0.0));

        GridPane.setColumnIndex(board_2_2, 2);
        GridPane.setHalignment(board_2_2, javafx.geometry.HPos.LEFT);
        GridPane.setRowIndex(board_2_2, 2);
        GridPane.setValignment(board_2_2, javafx.geometry.VPos.TOP);
        board_2_2.setFitHeight(90.0);
        board_2_2.setFitWidth(90.0);
        board_2_2.setPickOnBounds(true);
        board_2_2.setPreserveRatio(true);
        GridPane.setMargin(board_2_2, new Insets(15.0, 0.0, 0.0, 15.0));

        imageView0.setFitHeight(100.0);
        imageView0.setFitWidth(400.0);
        imageView0.setLayoutX(100.0);
        imageView0.setLayoutY(225.0);
        imageView0.setImage(new Image(getClass().getResource("/images/horizontal_stroke.png").toExternalForm()));

        imageView1.setFitHeight(400.0);
        imageView1.setFitWidth(100.0);
        imageView1.setLayoutX(175.0);
        imageView1.setImage(new Image(getClass().getResource("/images/vertical_stroke.png").toExternalForm()));

        imageView2.setFitHeight(100.0);
        imageView2.setFitWidth(400.0);
        imageView2.setLayoutX(110.0);
        imageView2.setLayoutY(75.0);
        imageView2.setRotate(180.0);
        imageView2.setImage(new Image(getClass().getResource("/images/horizontal_stroke.png").toExternalForm()));

        imageView3.setFitHeight(400.0);
        imageView3.setFitWidth(100.0);
        imageView3.setLayoutX(320.0);
        imageView3.setRotate(180.0);
        imageView3.setImage(new Image(getClass().getResource("/images/vertical_stroke.png").toExternalForm()));

        getChildren().add(imageView);
        getChildren().add(text_player_one);
        getChildren().add(btn_back);
        getChildren().add(text_player_two);
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getChildren().add(board_0_0);
        gridPane.getChildren().add(board_0_1);
        gridPane.getChildren().add(board_0_2);
        gridPane.getChildren().add(board_1_0);
        gridPane.getChildren().add(board_1_1);
        gridPane.getChildren().add(board_1_2);
        gridPane.getChildren().add(board_2_0);
        gridPane.getChildren().add(board_2_1);
        gridPane.getChildren().add(board_2_2);
        getChildren().add(gridPane);
        getChildren().add(imageView0);
        getChildren().add(imageView1);
        getChildren().add(imageView2);
        getChildren().add(imageView3);
        getChildren().add(win_condition);

        initButtonActions();

        checkStartingTurn();

    }

    public void initButtonActions() {
        board_0_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setXorO(board_0_0, 1);
                ServerConnection.getInstance().parseGameMove(Player.getInstance().getUserName(), ServerConnection.getInstance().getVsPlayerID(), 1);
                diableButtons();
            }
        });

        board_0_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setXorO(board_0_1, 2);
                diableButtons();
                ServerConnection.getInstance().parseGameMove(Player.getInstance().getUserName(), ServerConnection.getInstance().getVsPlayerID(), 2);
            }
        });

        board_0_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setXorO(board_0_2, 3);
                diableButtons();
                ServerConnection.getInstance().parseGameMove(Player.getInstance().getUserName(), ServerConnection.getInstance().getVsPlayerID(), 3);
            }
        });

        board_1_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setXorO(board_1_0, 4);
                diableButtons();
                ServerConnection.getInstance().parseGameMove(Player.getInstance().getUserName(), ServerConnection.getInstance().getVsPlayerID(), 4);
            }
        });

        board_1_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setXorO(board_1_1, 5);
                diableButtons();
                ServerConnection.getInstance().parseGameMove(Player.getInstance().getUserName(), ServerConnection.getInstance().getVsPlayerID(), 5);
            }
        });

        board_1_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setXorO(board_1_2, 6);
                diableButtons();
                ServerConnection.getInstance().parseGameMove(Player.getInstance().getUserName(), ServerConnection.getInstance().getVsPlayerID(), 6);
            }
        });

        board_2_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setXorO(board_2_0, 7);
                diableButtons();
                ServerConnection.getInstance().parseGameMove(Player.getInstance().getUserName(), ServerConnection.getInstance().getVsPlayerID(), 7);
            }
        });

        board_2_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setXorO(board_2_1, 8);
                diableButtons();
                ServerConnection.getInstance().parseGameMove(Player.getInstance().getUserName(), ServerConnection.getInstance().getVsPlayerID(), 8);
            }
        });

        board_2_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setXorO(board_2_2, 9);
                diableButtons();
                ServerConnection.getInstance().parseGameMove(Player.getInstance().getUserName(), ServerConnection.getInstance().getVsPlayerID(), 9);
            }
        });

        btn_back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                leavingAlert();
                if (result.get() == yesButton) {
                    ServerConnection.getInstance().playerLeft();
                    PlayOnlineScreen playOnline = new PlayOnlineScreen(currentStage);
                    Navigation.getInstance().navigate(playOnline, currentStage);
                    ServerConnection.getInstance().setVsPlayerID(null);
                }
            }
        });
    }

    public void setXorO(ImageView clickedButton, int index) {

        gameMoves += index;

        System.out.println("Moves:" + gameMoves);

        if (currentTurn % 2 != 0) {
            System.out.println("user:" + Player.getInstance().getUserName() + ", vs:" + ServerConnection.getInstance().getVsPlayerID() + ", index:" + index);
            clickedButton.setImage(new Image(getClass().getResource("/images/x_icon.png").toExternalForm()));
        } else {
            System.out.println("user:" + Player.getInstance().getUserName() + ", vs:" + ServerConnection.getInstance().getVsPlayerID() + ", index:" + index);
            clickedButton.setImage(new Image(getClass().getResource("/images/o_icon.png").toExternalForm()));
        }
        updateGameBoard(clickedButton);

        buttonList[index - 1] = null;

        currentTurn++;
    }

    public void updateGameBoard(ImageView clickedButton) {
        int row = GridPane.getRowIndex(clickedButton);
        int column = GridPane.getColumnIndex(clickedButton);

        if (currentTurn % 2 != 0) {
            gameBoardX[row][column] = 1;
            checkWinCondition(gameBoardX);

            for (int[] r : gameBoardX) {
                for (int c : r) {
                    System.out.print(c + " ");
                }
                System.out.println(); // print a new line after each row
            }

        } else {
            gameBoardO[row][column] = 1;
            checkWinCondition(gameBoardO);
            for (int[] r : gameBoardO) {
                for (int c : r) {
                    System.out.print(c + " ");
                }
                System.out.println(); // print a new line after each row
            }
        }

    }

    public void checkWinCondition(int currentGameBoard[][]) {

        if (currentGameBoard[0][0] == 1 && currentGameBoard[0][1] == 1 && currentGameBoard[0][2] == 1) {
            announceWinner(1);
        } else if (currentGameBoard[1][0] == 1 && currentGameBoard[1][1] == 1 && currentGameBoard[1][2] == 1) {
            announceWinner(2);
        } else if (currentGameBoard[2][0] == 1 && currentGameBoard[2][1] == 1 && currentGameBoard[2][2] == 1) {
            announceWinner(3);
        } else if (currentGameBoard[0][0] == 1 && currentGameBoard[1][1] == 1 && currentGameBoard[2][2] == 1) {
            announceWinner(4);
        } else if (currentGameBoard[0][2] == 1 && currentGameBoard[1][1] == 1 && currentGameBoard[2][0] == 1) {
            announceWinner(5);
        } else if (currentGameBoard[0][0] == 1 && currentGameBoard[1][0] == 1 && currentGameBoard[2][0] == 1) {
            announceWinner(6);
        } else if (currentGameBoard[0][1] == 1 && currentGameBoard[1][1] == 1 && currentGameBoard[2][1] == 1) {
            announceWinner(7);
        } else if (currentGameBoard[0][2] == 1 && currentGameBoard[1][2] == 1 && currentGameBoard[2][2] == 1) {
            announceWinner(8);
        } else if (currentTurn == 9 && isDraw == true) {
            noWinner = true;
            diableButtons();
        }
    }

    public void announceWinner(int winCondition) {
        if (currentTurn % 2 != 0) {
            drawWin(winCondition);
            if (startingTurn == 1) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Player.getInstance().setTotalScore(Player.getInstance().getTotalScore() + 5);
                        displayingVideo.displayVideo("/assets/videos/Winning.mp4");
                        ServerConnection.getInstance().updateScore(Player.getInstance().getUserName(), ServerConnection.getInstance().getVsPlayerID(), gameMoves);
                    }
                });

            } else {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        displayingVideo.displayVideo("/assets/videos/Losing.mp4");
                        Player.getInstance().setTotalScore(Player.getInstance().getTotalScore() - 3);
                    }
                });
            }

        } else {
            drawWin(winCondition);
            if (startingTurn == 1) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        displayingVideo.displayVideo("/assets/videos/Losing.mp4");
                        Player.getInstance().setTotalScore(Player.getInstance().getTotalScore() - 3);
                    }
                });
            } else {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ServerConnection.getInstance().updateScore(Player.getInstance().getUserName(), ServerConnection.getInstance().getVsPlayerID(), gameMoves);
                        Player.getInstance().setTotalScore(Player.getInstance().getTotalScore() + 5);
                        displayingVideo.displayVideo("/assets/videos/Winning.mp4");
                    }
                });
            }
        }
        isDraw = false;
        noWinner = false;
        diableButtons();
    }

    public void drawWin(int winCondition) {

        switch (winCondition) {
            case 1: {
                win_condition.setFitHeight(100.0);
                win_condition.setFitWidth(400.0);
                win_condition.setLayoutX(100.0);
                win_condition.setLayoutY(30.0);
                win_condition.setRotate(0);
                win_condition.setImage(new Image(getClass().getResource("/images/horizontal_stroke.png").toExternalForm()));
            }
            break;
            case 2: {
                win_condition.setFitHeight(100.0);
                win_condition.setFitWidth(400.0);
                win_condition.setLayoutX(100.0);
                win_condition.setLayoutY(150.0);
                win_condition.setRotate(0);
                win_condition.setImage(new Image(getClass().getResource("/images/horizontal_stroke.png").toExternalForm()));
            }
            break;
            case 3: {
                win_condition.setFitHeight(100.0);
                win_condition.setFitWidth(400.0);
                win_condition.setLayoutX(100.0);
                win_condition.setLayoutY(280.0);
                win_condition.setRotate(0);
                win_condition.setImage(new Image(getClass().getResource("/images/horizontal_stroke.png").toExternalForm()));
            }
            break;
            case 4: {
                win_condition.setFitHeight(500.0);
                win_condition.setFitWidth(100.0);
                win_condition.setLayoutX(237.0);
                win_condition.setLayoutY(-50.0);
                win_condition.setRotate(-44.0);
                win_condition.setImage(new Image(getClass().getResource("/images/vertical_stroke.png").toExternalForm()));
            }
            break;
            case 5: {
                win_condition.setFitHeight(500.0);
                win_condition.setFitWidth(100.0);
                win_condition.setLayoutX(237.0);
                win_condition.setLayoutY(-50.0);
                win_condition.setRotate(44.0);
                win_condition.setImage(new Image(getClass().getResource("/images/vertical_stroke.png").toExternalForm()));
            }
            break;
            case 6: {
                win_condition.setFitHeight(400.0);
                win_condition.setFitWidth(100.0);
                win_condition.setLayoutX(130.0);
                win_condition.setLayoutY(0);
                win_condition.setRotate(0);
                win_condition.setImage(new Image(getClass().getResource("/images/vertical_stroke.png").toExternalForm()));
            }
            break;
            case 7: {
                win_condition.setFitHeight(400.0);
                win_condition.setFitWidth(100.0);
                win_condition.setLayoutX(245.0);
                win_condition.setLayoutY(0);
                win_condition.setRotate(0);
                win_condition.setImage(new Image(getClass().getResource("/images/vertical_stroke.png").toExternalForm()));
            }
            break;
            case 8: {
                win_condition.setFitHeight(400.0);
                win_condition.setFitWidth(100.0);
                win_condition.setLayoutX(370.0);
                win_condition.setLayoutY(0);
                win_condition.setRotate(0);
                win_condition.setImage(new Image(getClass().getResource("/images/vertical_stroke.png").toExternalForm()));
            }
            break;
        }
    }

    public void computerLogic() {

        currentTurn++;
        checkWinCondition(gameBoardO);
    }

    public void diableButtons() {
        board_0_0.setDisable(true);
        board_1_0.setDisable(true);
        board_0_1.setDisable(true);
        board_0_2.setDisable(true);
        board_1_1.setDisable(true);
        board_1_2.setDisable(true);
        board_2_0.setDisable(true);
        board_2_1.setDisable(true);
        board_2_2.setDisable(true);
    }

    public void clearBoard() {
        board_0_0.setImage(null);
        board_1_0.setImage(null);
        board_0_1.setImage(null);
        board_0_2.setImage(null);
        board_1_1.setImage(null);
        board_1_2.setImage(null);
        board_2_0.setImage(null);
        board_2_1.setImage(null);
        board_2_2.setImage(null);

        win_condition.setImage(null);
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

    private void checkStartingTurn() {
        if (startingTurn != 1) {
            diableButtons();
        }
    }

    public void updateBoard(int move) {
        setXorO(buttonList[move - 1], move);
        System.out.println("move" + move);
        for (ImageView button : buttonList) {
            if (button != null) {
                button.setDisable(false);
            }
        }
    }
}

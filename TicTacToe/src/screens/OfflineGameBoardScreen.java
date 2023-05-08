package screens;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tictactoe.Navigation;

public class OfflineGameBoardScreen extends AnchorPane {

    protected final ImageView win_condition;
    protected final ImageView imageView;
    protected final Text text_player_one;
    protected final Text text_player_two;
    public final ImageView btn_back;
    public final ImageView btn_play_again;
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
    protected int playerOneScore;
    protected int playerTwoScore;
    protected Stage currentStage;

    protected Dialog warningDialog;
    protected ButtonType yesButton;
    protected Optional<ButtonType> result;

    public OfflineGameBoardScreen(Stage primaryStage) {

        currentStage = primaryStage;

        win_condition = new ImageView();
        imageView = new ImageView();
        text_player_one = new Text();
        btn_back = new ImageView();
        btn_play_again = new ImageView();
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
        isDraw = true;

        playerOneScore = 0;
        playerTwoScore = 0;

        currentTurn = 1;

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        imageView.setFitHeight(400.0);
        imageView.setFitWidth(600.0);
        imageView.setImage(new Image(getClass().getResource("/images/background.png").toExternalForm()));

        AnchorPane.setLeftAnchor(text_player_one, 15.0);
        AnchorPane.setTopAnchor(text_player_one, 15.0);
        text_player_one.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text_player_one.setStrokeWidth(0.0);
        text_player_one.setText("Player 1: " + playerOneScore);
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

        AnchorPane.setBottomAnchor(btn_play_again, 20.0);
        AnchorPane.setRightAnchor(btn_play_again, 20.0);
        btn_play_again.setFitHeight(50.0);
        btn_play_again.setFitWidth(50.0);
        btn_play_again.setLayoutX(25.0);
        btn_play_again.setLayoutY(15.0);
        btn_play_again.setPickOnBounds(true);
        btn_play_again.setPreserveRatio(true);
        btn_play_again.setVisible(false);
        btn_play_again.setDisable(true);
        btn_play_again.setImage(new Image(getClass().getResource("/images/again_button.png").toExternalForm()));

        AnchorPane.setRightAnchor(text_player_two, 15.0);
        AnchorPane.setTopAnchor(text_player_two, 15.0);
        text_player_two.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text_player_two.setStrokeWidth(0.0);
        text_player_two.setText("Player 2: " + playerTwoScore);
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
        getChildren().add(btn_play_again);

        initButtonActions();
    }

    public void initButtonActions() {
        board_0_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setXorO(board_0_0);
            }
        });

        board_0_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setXorO(board_0_1);
            }
        });

        board_0_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setXorO(board_0_2);
            }
        });

        board_1_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setXorO(board_1_0);
            }
        });

        board_1_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setXorO(board_1_1);
            }
        });

        board_1_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setXorO(board_1_2);
            }
        });

        board_2_0.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setXorO(board_2_0);
            }
        });

        board_2_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setXorO(board_2_1);
            }
        });

        board_2_2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setXorO(board_2_2);
            }
        });

        btn_play_again.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                playAgain();
            }
        });

        btn_back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                OfflineScreenBase offlineScreen = new OfflineScreenBase(currentStage);
                if (currentTurn > 1) {
                    leavingAlert();
                    if (result.get() == yesButton) {
                        Navigation.getInstance().navigate(offlineScreen, currentStage);
                    }
                } else {
                    Navigation.getInstance().navigate(offlineScreen, currentStage);
                }
            }
        });

    }


    public void setXorO(ImageView clickedButton) {
        if (currentTurn % 2 != 0) {
            clickedButton.setImage(new Image(getClass().getResource("/images/x_icon.png").toExternalForm()));
            updateGameBoard(clickedButton);
        } else {
            clickedButton.setImage(new Image(getClass().getResource("/images/o_icon.png").toExternalForm()));
            updateGameBoard(clickedButton);
        }
        currentTurn++;
        clickedButton.setDisable(true);
    }

    public void updateGameBoard(ImageView clickedButton) {
        int row = GridPane.getRowIndex(clickedButton);
        int column = GridPane.getColumnIndex(clickedButton);
        if (currentTurn % 2 != 0) {
            gameBoardX[row][column] = 1;
            if (currentTurn > 4) {
                checkWinCondition(gameBoardX);
            }
        } else {
            gameBoardO[row][column] = 1;
            if (currentTurn > 4) {
                checkWinCondition(gameBoardO);
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
            showDialog("DRAW");
            diableButtons();
            btn_play_again.setVisible(true);
            btn_play_again.setDisable(false);
        }
    }

    public void announceWinner(int winCondition) {
        if (currentTurn % 2 != 0) {
            System.out.println("X WON");
            drawWin(winCondition);
            playerOneScore++;
            text_player_one.setText("Player 1: " + playerOneScore);

            showDialog("Congratulation Player 1 Won");

        } else {
            System.out.println("O WON");
            drawWin(winCondition);
            playerTwoScore++;
            text_player_two.setText("Player 2: " + playerTwoScore);

            showDialog("Congratulation Player 2 Won");

        }
        isDraw = false;
        diableButtons();
        btn_play_again.setVisible(true);
        btn_play_again.setDisable(false);
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

    public void enableButtons() {
        board_0_0.setDisable(false);
        board_1_0.setDisable(false);
        board_0_1.setDisable(false);
        board_0_2.setDisable(false);
        board_1_1.setDisable(false);
        board_1_2.setDisable(false);
        board_2_0.setDisable(false);
        board_2_1.setDisable(false);
        board_2_2.setDisable(false);
    }

    public void playAgain() {
        gameBoardX = new int[3][3];
        gameBoardO = new int[3][3];
        isDraw = false;

        currentTurn = 1;

        clearBoard();
        enableButtons();
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

        btn_play_again.setDisable(true);
        btn_play_again.setVisible(false);
    }

    public void resetScores() {
        playerOneScore = 0;
        playerTwoScore = 0;
        text_player_one.setText("Player 1: " + playerOneScore);
        text_player_two.setText("Player 2: " + playerTwoScore);
    }

    public void showDialog(String msg) {

        Dialog dialog = new Dialog();
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #fff;");
        dialog.setContentText(msg);
        dialog.setTitle("Congratulation");

        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);

        dialog.getDialogPane().getButtonTypes().addAll(okButton);

        Button okButtonStyle = (Button) dialog.getDialogPane().lookupButton(okButton);
        okButtonStyle.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: #fff;");
        okButtonStyle.setAlignment(Pos.CENTER);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.get() == okButton) {
        } else {

        }

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
}

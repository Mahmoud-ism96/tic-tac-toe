package screens;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public  class GameBoardScreen extends AnchorPane {

    protected final ImageView imageView;
    protected final Text text_player_one;
    protected final Button btn_back;
    protected final Text text_player_two;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final ImageView imageView0;
    protected final ImageView imageView1;
    protected final ImageView imageView2;
    protected final ImageView imageView3;
    protected final ImageView imageView4;
    protected final ImageView imageView5;
    protected final ImageView imageView6;
    protected final ImageView imageView7;
    protected final ImageView imageView8;
    protected final ImageView imageView9;
    protected final ImageView imageView10;
    protected final ImageView imageView11;
    protected final ImageView imageView12;

    public GameBoardScreen() {

        imageView = new ImageView();
        text_player_one = new Text();
        btn_back = new Button();
        text_player_two = new Text();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        imageView0 = new ImageView();
        imageView1 = new ImageView();
        imageView2 = new ImageView();
        imageView3 = new ImageView();
        imageView4 = new ImageView();
        imageView5 = new ImageView();
        imageView6 = new ImageView();
        imageView7 = new ImageView();
        imageView8 = new ImageView();
        imageView9 = new ImageView();
        imageView10 = new ImageView();
        imageView11 = new ImageView();
        imageView12 = new ImageView();

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
        text_player_one.setText("Player 1:  0");

        AnchorPane.setBottomAnchor(btn_back, 15.0);
        AnchorPane.setLeftAnchor(btn_back, 15.0);
        btn_back.setMnemonicParsing(false);
        
        btn_back.setText("Back");

        AnchorPane.setRightAnchor(text_player_two, 15.0);
        AnchorPane.setTopAnchor(text_player_two, 15.0);
        text_player_two.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text_player_two.setStrokeWidth(0.0);
        text_player_two.setText("Player 2: 1");

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

        GridPane.setHalignment(imageView0, javafx.geometry.HPos.RIGHT);
        GridPane.setValignment(imageView0, javafx.geometry.VPos.BOTTOM);
        imageView0.setFitHeight(90.0);
        imageView0.setFitWidth(90.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("/images/x_icon.png").toExternalForm()));
        GridPane.setMargin(imageView0, new Insets(0.0, 15.0, 15.0, 0.0));

        GridPane.setColumnIndex(imageView1, 1);
        GridPane.setHalignment(imageView1, javafx.geometry.HPos.CENTER);
        GridPane.setValignment(imageView1, javafx.geometry.VPos.BOTTOM);
        imageView1.setFitHeight(90.0);
        imageView1.setFitWidth(90.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        GridPane.setMargin(imageView1, new Insets(0.0, 0.0, 15.0, 0.0));
        imageView1.setImage(new Image(getClass().getResource("/images/o_icon.png").toExternalForm()));

        GridPane.setColumnIndex(imageView2, 2);
        GridPane.setHalignment(imageView2, javafx.geometry.HPos.LEFT);
        GridPane.setValignment(imageView2, javafx.geometry.VPos.BOTTOM);
        imageView2.setFitHeight(90.0);
        imageView2.setFitWidth(90.0);
        imageView2.setPickOnBounds(true);
        imageView2.setPreserveRatio(true);
        imageView2.setImage(new Image(getClass().getResource("/images/o_icon.png").toExternalForm()));
        GridPane.setMargin(imageView2, new Insets(0.0, 0.0, 15.0, 15.0));

        GridPane.setHalignment(imageView3, javafx.geometry.HPos.RIGHT);
        GridPane.setRowIndex(imageView3, 1);
        GridPane.setValignment(imageView3, javafx.geometry.VPos.CENTER);
        imageView3.setFitHeight(90.0);
        imageView3.setFitWidth(90.0);
        imageView3.setPickOnBounds(true);
        imageView3.setPreserveRatio(true);
        GridPane.setMargin(imageView3, new Insets(0.0, 15.0, 0.0, 0.0));

        GridPane.setColumnIndex(imageView4, 1);
        GridPane.setHalignment(imageView4, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(imageView4, 1);
        GridPane.setValignment(imageView4, javafx.geometry.VPos.CENTER);
        imageView4.setFitHeight(90.0);
        imageView4.setFitWidth(90.0);
        imageView4.setPickOnBounds(true);
        imageView4.setPreserveRatio(true);
        imageView4.setImage(new Image(getClass().getResource("/images/x_icon.png").toExternalForm()));

        GridPane.setColumnIndex(imageView5, 2);
        GridPane.setHalignment(imageView5, javafx.geometry.HPos.LEFT);
        GridPane.setRowIndex(imageView5, 1);
        GridPane.setValignment(imageView5, javafx.geometry.VPos.CENTER);
        imageView5.setFitHeight(90.0);
        imageView5.setFitWidth(90.0);
        imageView5.setPickOnBounds(true);
        imageView5.setPreserveRatio(true);
        GridPane.setMargin(imageView5, new Insets(0.0, 0.0, 0.0, 15.0));

        GridPane.setHalignment(imageView6, javafx.geometry.HPos.RIGHT);
        GridPane.setRowIndex(imageView6, 2);
        GridPane.setValignment(imageView6, javafx.geometry.VPos.TOP);
        imageView6.setFitHeight(90.0);
        imageView6.setFitWidth(90.0);
        imageView6.setPickOnBounds(true);
        imageView6.setPreserveRatio(true);
        GridPane.setMargin(imageView6, new Insets(15.0, 15.0, 0.0, 0.0));

        GridPane.setColumnIndex(imageView7, 1);
        GridPane.setHalignment(imageView7, javafx.geometry.HPos.CENTER);
        GridPane.setRowIndex(imageView7, 2);
        GridPane.setValignment(imageView7, javafx.geometry.VPos.TOP);
        imageView7.setFitHeight(90.0);
        imageView7.setFitWidth(90.0);
        imageView7.setPickOnBounds(true);
        imageView7.setPreserveRatio(true);
        GridPane.setMargin(imageView7, new Insets(15.0, 0.0, 0.0, 0.0));

        GridPane.setColumnIndex(imageView8, 2);
        GridPane.setHalignment(imageView8, javafx.geometry.HPos.LEFT);
        GridPane.setRowIndex(imageView8, 2);
        GridPane.setValignment(imageView8, javafx.geometry.VPos.TOP);
        imageView8.setFitHeight(90.0);
        imageView8.setFitWidth(90.0);
        imageView8.setPickOnBounds(true);
        imageView8.setPreserveRatio(true);
        GridPane.setMargin(imageView8, new Insets(15.0, 0.0, 0.0, 15.0));

        imageView9.setFitHeight(100.0);
        imageView9.setFitWidth(400.0);
        imageView9.setLayoutX(100.0);
        imageView9.setLayoutY(225.0);
        imageView9.setImage(new Image(getClass().getResource("/images/horizontal_stroke.png").toExternalForm()));

        imageView10.setFitHeight(400.0);
        imageView10.setFitWidth(100.0);
        imageView10.setLayoutX(175.0);
        imageView10.setImage(new Image(getClass().getResource("/images/vertical_stroke.png").toExternalForm()));

        imageView11.setFitHeight(100.0);
        imageView11.setFitWidth(400.0);
        imageView11.setLayoutX(110.0);
        imageView11.setLayoutY(75.0);
        imageView11.setRotate(180.0);
        imageView11.setImage(new Image(getClass().getResource("/images/horizontal_stroke.png").toExternalForm()));

        imageView12.setFitHeight(400.0);
        imageView12.setFitWidth(100.0);
        imageView12.setLayoutX(320.0);
        imageView12.setRotate(180.0);
        imageView12.setImage(new Image(getClass().getResource("/images/vertical_stroke.png").toExternalForm()));

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
        gridPane.getChildren().add(imageView0);
        gridPane.getChildren().add(imageView1);
        gridPane.getChildren().add(imageView2);
        gridPane.getChildren().add(imageView3);
        gridPane.getChildren().add(imageView4);
        gridPane.getChildren().add(imageView5);
        gridPane.getChildren().add(imageView6);
        gridPane.getChildren().add(imageView7);
        gridPane.getChildren().add(imageView8);
        getChildren().add(gridPane);
        getChildren().add(imageView9);
        getChildren().add(imageView10);
        getChildren().add(imageView11);
        getChildren().add(imageView12);

    }
}

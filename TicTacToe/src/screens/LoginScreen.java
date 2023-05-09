package screens;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tictactoe.Navigation;
import tictactoe.ServerConnection;

public class LoginScreen extends AnchorPane {

    protected final TextField tv_username;
    protected final TextField tv_password;
    protected final Button btn_login;
    protected final ImageView background;
    protected final ImageView icon_username;
    protected final ImageView icon_password;
    protected final ImageView icon_login;
    public final ImageView icon_back;
    public final Text txt_signUp;
    protected Image img_user;
    protected Image img_password;
    protected Image img_login;
    protected Image img_background;
    protected Image img_back;

    protected Stage currentStage;

    String userName;
    String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginScreen(Stage primaryStage) throws IOException {

        currentStage = primaryStage;

        background = new ImageView();
        icon_username = new ImageView();
        icon_password = new ImageView();
        icon_login = new ImageView();
        icon_back = new ImageView();
        txt_signUp = new Text();
        tv_username = new TextField();
        tv_password = new TextField();
        btn_login = new Button();

        img_user = new Image(getClass().getResourceAsStream("/images/user.png"));
        img_password = new Image(getClass().getResourceAsStream("/images/password.png"));
        img_login = new Image(getClass().getResourceAsStream("/images/login.png"));
        img_background = new Image(getClass().getResourceAsStream("/images/background.png"));
        img_back = new Image(getClass().getResourceAsStream("/images/back_button.png"));

        background.setImage(img_background);
        icon_username.setImage(img_user);
        icon_password.setImage(img_password);
        icon_login.setImage(img_login);
        icon_back.setImage(img_back);

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        AnchorPane.setBottomAnchor(background, 0.0);
        AnchorPane.setLeftAnchor(background, 0.0);
        AnchorPane.setRightAnchor(background, 0.0);
        AnchorPane.setTopAnchor(background, 0.0);
        background.setFitHeight(400.0);
        background.setFitWidth(600.0);
        background.setPickOnBounds(true);
        background.setPreserveRatio(true);

        AnchorPane.setLeftAnchor(icon_username, 200.0);
        AnchorPane.setRightAnchor(icon_username, 200.0);
        icon_username.setFitHeight(30.0);
        icon_username.setFitWidth(30.0);
        icon_username.setLayoutY(176.0);
        icon_username.setPickOnBounds(true);
        icon_username.setPreserveRatio(true);

        AnchorPane.setLeftAnchor(icon_password, 200.0);
        AnchorPane.setRightAnchor(icon_password, 200.0);
        icon_password.setFitHeight(30.0);
        icon_password.setFitWidth(30.0);
        icon_password.setLayoutY(230.0);
        icon_password.setPickOnBounds(true);
        icon_password.setPreserveRatio(true);

        AnchorPane.setLeftAnchor(icon_login, 250.0);
        AnchorPane.setRightAnchor(icon_login, 250.0);
        icon_login.setFitHeight(100.0);
        icon_login.setFitWidth(100.0);
        icon_login.setLayoutY(35.0);
        icon_login.setPickOnBounds(true);
        icon_login.setPreserveRatio(true);

        icon_back.setFitHeight(50.0);
        icon_back.setFitWidth(50.0);
        icon_back.setLayoutX(25.0);
        icon_back.setLayoutY(15.0);
        icon_back.setPickOnBounds(true);
        icon_back.setPreserveRatio(true);

        AnchorPane.setLeftAnchor(txt_signUp, 200.0);
        AnchorPane.setRightAnchor(txt_signUp, 200.0);
        txt_signUp.setLayoutY(344.0);
        txt_signUp.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        txt_signUp.setStrokeWidth(0.0);
        txt_signUp.setText("Donot have Account? Register here");
        txt_signUp.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        AnchorPane.setLeftAnchor(tv_username, 230.0);
        AnchorPane.setRightAnchor(tv_username, 230.0);
        tv_username.setLayoutY(178.0);
        tv_username.setPromptText("username");
        tv_username.setStyle("-fx-border-color: BLACK;");

        AnchorPane.setLeftAnchor(tv_password, 230.0);
        AnchorPane.setRightAnchor(tv_password, 230.0);
        tv_password.setLayoutY(232.0);
        tv_password.setPromptText("password");
        tv_password.setStyle("-fx-border-color: BLACK;");

        AnchorPane.setLeftAnchor(btn_login, 253.0);
        AnchorPane.setRightAnchor(btn_login, 253.0);
        btn_login.setLayoutY(280.0);
        btn_login.setMnemonicParsing(false);
        btn_login.setText("Login");
        btn_login.getStyleClass().add("button");
        btn_login.setStyle("-fx-font-size:12;");

        getChildren().add(background);
        getChildren().add(icon_username);
        getChildren().add(icon_password);
        getChildren().add(icon_login);
        getChildren().add(icon_back);
        getChildren().add(txt_signUp);
        getChildren().add(tv_username);
        getChildren().add(tv_password);
        getChildren().add(btn_login);

        initButtonActions();
    }

    public void initButtonActions() {
        txt_signUp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                RegisterScreen registerScreen = new RegisterScreen(currentStage);
                Navigation.getInstance().navigate(registerScreen, currentStage);
            }
        });

        icon_back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                StartScreenBase startScreen = new StartScreenBase(currentStage);
                Navigation.getInstance().navigate(startScreen, currentStage);
            }
        });
        btn_login.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                userName = tv_username.getText();
                password = tv_password.getText();

                ServerConnection.getInstance().parseSignIn(userName, password);

            }
        });

    }

}

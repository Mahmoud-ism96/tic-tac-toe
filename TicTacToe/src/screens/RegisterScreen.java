package screens;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tictactoe.Navigation;
import tictactoe.ServerConnection;

public class RegisterScreen extends AnchorPane {

    protected final ImageView img_background;
    protected final TextField tv_username;
    protected final TextField tv_displayname;
    protected final PasswordField tv_password;
    protected final PasswordField tv_confirm_password;
    protected final Button btn_register;
    public final Text txt_signIn;
    protected final ImageView icon_register;
    protected final ImageView icon_username;
    protected final ImageView icon_diaplayname;
    protected final ImageView icon_password;
    protected final ImageView icon_confirm_password;
    public ImageView icon_back;

    protected Image img_user;
    protected Image img_dispalyname;
    protected Image img_password;
    protected Image img_register;
    protected Image background;
    protected Image img_back;
    protected Image img_confirm_password;

    protected Stage currentStage;

    public RegisterScreen(Stage primaryStage) {

        currentStage = primaryStage;

        img_background = new ImageView();
        tv_username = new TextField();
        tv_displayname = new TextField();
        tv_password = new PasswordField();
        tv_confirm_password = new PasswordField();
        btn_register = new Button();
        txt_signIn = new Text();
        icon_register = new ImageView();
        icon_diaplayname = new ImageView();
        icon_username = new ImageView();
        icon_password = new ImageView();
        icon_confirm_password = new ImageView();
        icon_back = new ImageView();

        img_user = new Image(getClass().getResourceAsStream("/images/user.png"));
        img_dispalyname = new Image(getClass().getResourceAsStream("/images/displayname.png"));
        img_password = new Image(getClass().getResourceAsStream("/images/password.png"));
        img_confirm_password = new Image(getClass().getResourceAsStream("/images/confirm.png"));
        img_register = new Image(getClass().getResourceAsStream("/images/register.png"));
        background = new Image(getClass().getResourceAsStream("/images/background.png"));
        img_back = new Image(getClass().getResourceAsStream("/images/back_button.png"));

        img_background.setImage(background);
        icon_username.setImage(img_user);
        icon_diaplayname.setImage(img_dispalyname);
        icon_password.setImage(img_password);
        icon_register.setImage(img_register);
        icon_back.setImage(img_back);
        icon_confirm_password.setImage(img_confirm_password);

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        AnchorPane.setBottomAnchor(img_background, 0.0);
        AnchorPane.setLeftAnchor(img_background, 0.0);
        AnchorPane.setRightAnchor(img_background, 0.0);
        AnchorPane.setTopAnchor(img_background, 0.0);
        img_background.setFitHeight(400.0);
        img_background.setFitWidth(600.0);
        img_background.setPickOnBounds(true);
        img_background.setPreserveRatio(true);

        AnchorPane.setLeftAnchor(tv_username, 230.0);
        AnchorPane.setRightAnchor(tv_username, 230.0);
        tv_username.setLayoutY(120.0);
        tv_username.setPromptText("username");
        tv_username.setStyle("-fx-border-color: BLACK;");

        AnchorPane.setLeftAnchor(tv_displayname, 230.0);
        AnchorPane.setRightAnchor(tv_displayname, 230.0);
        tv_displayname.setLayoutY(170.0);
        tv_displayname.setPromptText("display name");
        tv_displayname.setStyle("-fx-border-color: BLACK;");

        AnchorPane.setLeftAnchor(tv_password, 230.0);
        AnchorPane.setRightAnchor(tv_password, 230.0);
        tv_password.setLayoutY(220.0);
        tv_password.setPromptText("password");
        tv_password.setStyle("-fx-border-color: BLACK;");

        AnchorPane.setLeftAnchor(tv_confirm_password, 230.0);
        AnchorPane.setRightAnchor(tv_confirm_password, 230.0);
        tv_confirm_password.setLayoutY(270.0);
        tv_confirm_password.setPromptText("confirm password");
        tv_confirm_password.setStyle("-fx-border-color: BLACK;");

        AnchorPane.setLeftAnchor(btn_register, 250.0);
        AnchorPane.setRightAnchor(btn_register, 250.0);
        btn_register.setLayoutY(310.0);
        btn_register.setMnemonicParsing(false);
        btn_register.getStyleClass().add("button");
        btn_register.setStyle("-fx-font-size:12;");
        btn_register.setText("Register");
        btn_register.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        AnchorPane.setLeftAnchor(txt_signIn, 250.0);
        AnchorPane.setRightAnchor(txt_signIn, 250.0);
        txt_signIn.setLayoutY(362.0);
        txt_signIn.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        txt_signIn.setStrokeWidth(0.0);
        txt_signIn.setText("Have An Account?");

        AnchorPane.setLeftAnchor(icon_register, 250.0);
        AnchorPane.setRightAnchor(icon_register, 250.0);
        icon_register.setFitHeight(100.0);
        icon_register.setFitWidth(100.0);
        icon_register.setLayoutY(14.0);
        icon_register.setPickOnBounds(true);
        icon_register.setPreserveRatio(true);

        AnchorPane.setLeftAnchor(icon_username, 200.0);
        AnchorPane.setRightAnchor(icon_username, 200.0);
        icon_username.setFitHeight(30.0);
        icon_username.setFitWidth(30.0);
        icon_username.setLayoutY(119.0);
        icon_username.setPickOnBounds(true);
        icon_username.setPreserveRatio(true);

        AnchorPane.setLeftAnchor(icon_diaplayname, 200.0);
        AnchorPane.setRightAnchor(icon_diaplayname, 200.0);
        icon_diaplayname.setFitHeight(27.0);
        icon_diaplayname.setFitWidth(27.0);
        icon_diaplayname.setLayoutY(170.0);
        icon_diaplayname.setPickOnBounds(true);
        icon_diaplayname.setPreserveRatio(true);

        AnchorPane.setLeftAnchor(icon_password, 200.0);
        AnchorPane.setRightAnchor(icon_password, 200.0);
        icon_password.setFitHeight(30.0);
        icon_password.setFitWidth(30.0);
        icon_password.setLayoutY(220.0);
        icon_password.setPickOnBounds(true);
        icon_password.setPreserveRatio(true);

        AnchorPane.setLeftAnchor(icon_confirm_password, 197.0);
        AnchorPane.setRightAnchor(icon_confirm_password, 197.0);
        icon_confirm_password.setFitHeight(30.0);
        icon_confirm_password.setFitWidth(30.0);
        icon_confirm_password.setLayoutY(270.0);
        icon_confirm_password.setPickOnBounds(true);
        icon_confirm_password.setPreserveRatio(true);

        icon_back.setFitHeight(50.0);
        icon_back.setFitWidth(50.0);
        icon_back.setLayoutX(25.0);
        icon_back.setLayoutY(15.0);
        icon_back.setPickOnBounds(true);
        icon_back.setPreserveRatio(true);

        getChildren().add(img_background);
        getChildren().add(tv_username);
        getChildren().add(tv_displayname);
        getChildren().add(tv_password);
        getChildren().add(tv_confirm_password);
        getChildren().add(btn_register);
        getChildren().add(txt_signIn);
        getChildren().add(icon_register);
        getChildren().add(icon_username);
        getChildren().add(icon_diaplayname);
        getChildren().add(icon_password);
        getChildren().add(icon_confirm_password);
        getChildren().add(icon_back);

        initButtonActions();

    }

    public void initButtonActions() {
        btn_register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (validation()) {
                    String username = tv_username.getText();
                    String displayname = tv_displayname.getText();
                    String password = tv_password.getText();

                    ServerConnection.getInstance().parseSignUp(username, displayname, password);
                }
            }
        });

        txt_signIn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    LoginScreen loginScreen = new LoginScreen(currentStage);
                    Navigation.getInstance().navigate(loginScreen, currentStage);
                } catch (IOException ex) {
                    Logger.getLogger(RegisterScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        icon_back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                StartScreenBase startScreen = new StartScreenBase(currentStage);
                Navigation.getInstance().navigate(startScreen, currentStage);
            }
        });
    }

    public boolean validation() {

        String name = tv_username.getText();
        String password = tv_password.getText();
        String confirmPassword = tv_confirm_password.getText();

        Boolean valid = false;

        if (name.length() < 6 && name.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Username should be at least 6 characters long");
            alert.showAndWait();

        } else if (!password.matches(".*[a-zA-Z].*") || !password.matches(".*\\d.*")) {
            System.out.println("Password should contain both letters and numbers");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Password should contain both letters and numbers");
            alert.showAndWait();
        } else if (!password.equals(confirmPassword)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Password and confirmation password do not match");
            alert.showAndWait();
        } else {
            valid = true;
        }
        return valid;
    }
}

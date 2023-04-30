package tictactoe;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static javafx.scene.paint.Color.BLUE;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public  class LoginScreen extends AnchorPane {

    protected final ImageView background;
    protected final ImageView icon_username;
    protected final ImageView icon_password;
    protected final ImageView icon_login;
    protected final ImageView icon_back;
    protected final Text txt_signUp;
    protected final TextField tv_username;
    protected final TextField tv_password;
    protected final Button btn_login;

    
    protected Image img_user;
    protected Image img_password;
    protected Image img_login;
    protected Image img_background;
    protected  Image img_back;

    public LoginScreen() {

        background = new ImageView();
        icon_username = new ImageView();
        icon_password = new ImageView();
        icon_login = new ImageView();
        icon_back = new ImageView();
        txt_signUp = new Text();
        tv_username = new TextField();
        tv_password = new TextField();
        btn_login = new Button();
        
        
         img_user=new Image(getClass().getResourceAsStream("/images/user.png"));
        img_password=new Image(getClass().getResourceAsStream("/images/password.png"));
        img_login=new Image(getClass().getResourceAsStream("/images/login.png"));
        img_background=new Image(getClass().getResourceAsStream("/images/background.jpg"));
        img_back=new Image(getClass().getResourceAsStream("/images/back.png"));

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
        background.setFitWidth(607.0);
        background.setLayoutX(-1.0);
        background.setPickOnBounds(true);
        background.setPreserveRatio(true);

        icon_username.setFitHeight(30.0);
        icon_username.setFitWidth(30.0);
        icon_username.setLayoutX(203.0);
        icon_username.setLayoutY(176.0);
        icon_username.setPickOnBounds(true);
        icon_username.setPreserveRatio(true);

        icon_password.setFitHeight(30.0);
        icon_password.setFitWidth(30.0);
        icon_password.setLayoutX(203.0);
        icon_password.setLayoutY(230.0);
        icon_password.setPickOnBounds(true);
        icon_password.setPreserveRatio(true);

        icon_login.setFitHeight(100.0);
        icon_login.setFitWidth(100.0);
        icon_login.setLayoutX(268.0);
        icon_login.setLayoutY(35.0);
        icon_login.setPickOnBounds(true);
        icon_login.setPreserveRatio(true);

        icon_back.setFitHeight(30.0);
        icon_back.setFitWidth(30.0);
        icon_back.setLayoutX(14.0);
        icon_back.setLayoutY(14.0);
        icon_back.setPickOnBounds(true);
        icon_back.setPreserveRatio(true);

        txt_signUp.setLayoutX(225.0);
        txt_signUp.setLayoutY(344.0);
        txt_signUp.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        txt_signUp.setStrokeWidth(0.0);
        txt_signUp.setText("Donot have Account? Register here");
        txt_signUp.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        tv_username.setLayoutX(243.0);
        tv_username.setLayoutY(178.0);
        tv_username.setPromptText("username");
        tv_username.setStyle("-fx-border-color: BLACK;");

        tv_password.setLayoutX(243.0);
        tv_password.setLayoutY(232.0);
        tv_password.setPromptText("password");
        tv_password.setStyle("-fx-border-color: BLACK;");

        btn_login.setLayoutX(263.0);
        btn_login.setLayoutY(289.0);
        btn_login.setMnemonicParsing(false);
        btn_login.setPrefHeight(25.0);
        btn_login.setPrefWidth(110.0);
        btn_login.setStyle("-fx-background-color: BLACK; -fx-background-radius: 25;");
        btn_login.setText("Login");
        btn_login.setTextFill(javafx.scene.paint.Color.WHITE);
        btn_login.setFont(new Font("System Bold", 12.0));

        getChildren().add(background);
        getChildren().add(icon_username);
        getChildren().add(icon_password);
        getChildren().add(icon_login);
        getChildren().add(icon_back);
        getChildren().add(txt_signUp);
        getChildren().add(tv_username);
        getChildren().add(tv_password);
        getChildren().add(btn_login);
        
        txt_signUp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                txt_signUp.setFill(BLUE);
               
              
            }

    });
       icon_back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
               
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION); 
                alert.setHeaderText("Leave Game");
                alert.setContentText("Are You Sure Want To Leave? ");
                alert.showAndWait();
            }

    });

    }

   

}
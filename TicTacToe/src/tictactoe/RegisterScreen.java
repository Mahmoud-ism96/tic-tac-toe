package tictactoe;


import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static javafx.scene.paint.Color.BLUE;
import javafx.scene.text.Text;

public  class RegisterScreen extends AnchorPane {

    protected final ImageView img_background;
    protected final TextField tv_username;
    protected final PasswordField tv_password;
    protected final PasswordField tv_confirm_password;
    protected final Button btn_register;
    protected final Text txt_signIn;
    protected final ImageView icon_register;
    protected final ImageView icon_username;
    protected final ImageView icon_password;
    protected final ImageView icon_confirm_password;
    protected final ImageView icon_back;
    
    protected Image img_user;
    protected Image img_password;
    protected Image img_register;
    protected Image background;
    protected  Image img_back;
     protected  Image img_confirm_password;

    public RegisterScreen() {

        img_background = new ImageView();
        tv_username = new TextField();
        tv_password = new PasswordField();
        tv_confirm_password = new PasswordField();
        btn_register = new Button();
        txt_signIn = new Text();
        icon_register = new ImageView();
        icon_username = new ImageView();
        icon_password = new ImageView();
        icon_confirm_password = new ImageView();
        icon_back = new ImageView();
        
        img_user = new Image(getClass().getResourceAsStream("/images/user.png"));
        img_password = new Image(getClass().getResourceAsStream("/images/password.png"));
        img_confirm_password = new Image(getClass().getResourceAsStream("/images/confirm.png"));
        img_register = new Image(getClass().getResourceAsStream("/images/register.png"));
        background = new Image(getClass().getResourceAsStream("/images/background.jpg"));
        img_back = new Image(getClass().getResourceAsStream("/images/back.png"));
        
        img_background.setImage(background);
        icon_username.setImage(img_user);
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

        tv_username.setLayoutX(245.0);
        tv_username.setLayoutY(151.0);
        tv_username.setPromptText("username");
        tv_username.setStyle("-fx-border-color: BLACK;");

        tv_password.setLayoutX(245.0);
        tv_password.setLayoutY(207.0);
        tv_password.setPromptText("password");
        tv_password.setStyle("-fx-border-color: BLACK;");

        tv_confirm_password.setLayoutX(245.0);
        tv_confirm_password.setLayoutY(261.0);
        tv_confirm_password.setPromptText("confirm password");
        tv_confirm_password.setStyle("-fx-border-color: BLACK;");

        btn_register.setLayoutX(257.0);
        btn_register.setLayoutY(315.0);
        btn_register.setMnemonicParsing(false);
        btn_register.setPrefHeight(25.0);
        btn_register.setPrefWidth(121.0);
        btn_register.setStyle("-fx-background-color: BLACK; -fx-background-radius: 25;");
        btn_register.setText("Register");
        btn_register.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        btn_register.setTextFill(javafx.scene.paint.Color.WHITE);

        txt_signIn.setLayoutX(272.0);
        txt_signIn.setLayoutY(362.0);
        txt_signIn.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        txt_signIn.setStrokeWidth(0.0);
        txt_signIn.setText("Have An Account?");

        icon_register.setFitHeight(100.0);
        icon_register.setFitWidth(100.0);
        icon_register.setLayoutX(263.0);
        icon_register.setLayoutY(14.0);
        icon_register.setPickOnBounds(true);
        icon_register.setPreserveRatio(true);

        icon_username.setFitHeight(30.0);
        icon_username.setFitWidth(30.0);
        icon_username.setLayoutX(204.0);
        icon_username.setLayoutY(149.0);
        icon_username.setPickOnBounds(true);
        icon_username.setPreserveRatio(true);

        icon_password.setFitHeight(30.0);
        icon_password.setFitWidth(30.0);
        icon_password.setLayoutX(204.0);
        icon_password.setLayoutY(205.0);
        icon_password.setPickOnBounds(true);
        icon_password.setPreserveRatio(true);

        icon_confirm_password.setFitHeight(30.0);
        icon_confirm_password.setFitWidth(30.0);
        icon_confirm_password.setLayoutX(204.0);
        icon_confirm_password.setLayoutY(259.0);
        icon_confirm_password.setPickOnBounds(true);
        icon_confirm_password.setPreserveRatio(true);

        icon_back.setFitHeight(30.0);
        icon_back.setFitWidth(30.0);
        icon_back.setLayoutX(14.0);
        icon_back.setLayoutY(14.0);
        icon_back.setPickOnBounds(true);
        icon_back.setPreserveRatio(true);

        getChildren().add(img_background);
        getChildren().add(tv_username);
        getChildren().add(tv_password);
        getChildren().add(tv_confirm_password);
        getChildren().add(btn_register);
        getChildren().add(txt_signIn);
        getChildren().add(icon_register);
        getChildren().add(icon_username);
        getChildren().add(icon_password);
        getChildren().add(icon_confirm_password);
        getChildren().add(icon_back);
        
        txt_signIn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                txt_signIn.setFill(BLUE);
                
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

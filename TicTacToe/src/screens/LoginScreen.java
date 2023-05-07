package screens;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

   
 
    

    String userName ;
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
    
    protected Image img_user;
    protected Image img_password;
    protected Image img_login;
    protected Image img_background;
    protected Image img_back;

    protected Stage currentStage;
    
    

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

        icon_back.setFitHeight(50.0);
        icon_back.setFitWidth(50.0);
        icon_back.setLayoutX(25.0);
        icon_back.setLayoutY(15.0);
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
                navigate(registerScreen);
            }
        });

        icon_back.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                StartScreenBase startScreen = new StartScreenBase(currentStage);
                navigate(startScreen);
            }
        });
         btn_login.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
        public void handle(MouseEvent event) {
        userName = tv_username.getText();
        password = tv_password.getText();
        
        JsonObject jsonObject = new JsonObject();
        JsonObject signinObject = new JsonObject();
        JsonObject requestData = new JsonObject();
        signinObject.addProperty("username", userName);
        signinObject.addProperty("password", password);
        requestData.addProperty("request", "signup");
        jsonObject.add("data", signinObject);
        jsonObject.add("request", requestData);
        String jsonString = jsonObject.toString();
                 Socket socket = null;
                 PrintWriter out = null;
                 OutputStream  outPutStream = null ;
        try {
             socket = new Socket("127.0.0.1", 1234);
             outPutStream = socket.getOutputStream();
              out = new PrintWriter(outPutStream,true);
        } catch (IOException ex) {
            Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
        }      
        
        
        out.println(jsonString);
        
                try {
                    outPutStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            ComputerGameBoardScreen computerBoard = new ComputerGameBoardScreen(currentStage);
            navigate(computerBoard);
            }
        });
        
    }

    public void navigate(Parent screen) {
        screen.setStyle("-fx-font-family: Stroke;");
        Scene scene = new Scene(screen);
        scene.getStylesheets().add(getClass().getResource("/assets/css.css").toExternalForm());
        currentStage.setScene(scene);
    }
      
}



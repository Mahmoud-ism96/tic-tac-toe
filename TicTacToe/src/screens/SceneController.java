package screens;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    public void switchToPlayOnlineScreen(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("PlayOnlineScreen.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();  
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToOnlinePlayerListScreen(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("OnlinePlayerListScreen.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();  
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
  
}

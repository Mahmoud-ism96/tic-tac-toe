/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import java.io.File;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author HP
 */
public class DisplayingVideo {
        public void displayVideo(String path){
     
            Media media = new Media(getClass().getResource(path).toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);

            final Stage dialog = new Stage();
            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.initModality(Modality.APPLICATION_MODAL);
            mediaView.fitHeightProperty().bind(dialog.heightProperty());

            VBox dialogVbox = new VBox(50);
            dialogVbox.setPrefSize(400, 250);
            dialogVbox.getChildren().add(mediaView);

            Scene dialogScene = new Scene(dialogVbox);
            dialog.setScene(dialogScene);
            dialog.show();
            mediaPlayer.play();
            mediaPlayer.setOnEndOfMedia(() -> {
                dialog.close();
            });
     }
    
    
}

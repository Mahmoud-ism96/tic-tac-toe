/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author LENOVO
 */
public final class Navigation {

    private static Navigation INSTANCE;

    public static Navigation getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Navigation();
        }

        return INSTANCE;
    }

    public void navigate(Parent screen, Stage currentStage) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                screen.setStyle("-fx-font-family: Stroke;");
                Scene scene = new Scene(screen);
                scene.getStylesheets().add(getClass().getResource("/assets/css.css").toExternalForm());
                currentStage.setScene(scene);
            }
        });

    }

}

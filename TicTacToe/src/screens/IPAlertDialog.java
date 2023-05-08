/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Mahmoud Ism
 */
public class IPAlertDialog {

    static String serverIP;

    public static String display() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField text1 = new TextField();

        Button button = new Button("Submit");
        button.setOnAction(e -> {
            serverIP = text1.getText();
            stage.close();
        });

        Label label1 = new Label("Enter Server IP ");
        Label label2 = new Label("IP:");

        GridPane layout = new GridPane();

        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(5);
        layout.setHgap(5);

        layout.add(text1, 1, 1);
        layout.add(button, 1, 3);
        layout.add(label1, 1, 0);
        layout.add(label2, 0, 1);
        
        GridPane.setHalignment(label1, HPos.CENTER);
        GridPane.setValignment(label1, VPos.CENTER);
        GridPane.setHalignment(button, HPos.CENTER);
        GridPane.setValignment(button, VPos.CENTER);
        

        Scene scene = new Scene(layout, 190, 110);
        stage.setTitle("IP");
        stage.setScene(scene);
        stage.showAndWait();

        return serverIP;
    }
}

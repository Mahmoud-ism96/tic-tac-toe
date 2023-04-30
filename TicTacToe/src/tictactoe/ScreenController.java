/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author HP
 */
public class ScreenController {
     
    static LoginScreen loginScreen = new LoginScreen();
    static RegisterScreen registerScreen = new RegisterScreen();
  
     public static void viewPane(AnchorPane pane)
    {
        
        loginScreen.setVisible(false);
        registerScreen.setVisible(false);
       
        pane.setVisible(true);
    }
}

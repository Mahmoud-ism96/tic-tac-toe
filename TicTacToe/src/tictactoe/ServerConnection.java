/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author Mahmoud Ism
 */
public final class ServerConnection {

    private static ServerConnection INSTANCE;
    
    private ServerConnection() {        
    }
    
    public static ServerConnection getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ServerConnection();
        }
        
        return INSTANCE;
    }


}
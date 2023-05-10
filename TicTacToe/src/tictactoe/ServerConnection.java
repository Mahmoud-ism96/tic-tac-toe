/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 *
 * @author Mahmoud Ism
 */
public final class ServerConnection {

    private static ServerConnection INSTANCE;
    private static boolean connectionState = false;
    private Socket serverSocket;
    private OutputStream outPutStream;
    private PrintStream printStream;
    private DataInputStream inputStream;

    private ServerConnection() {
    }

    public static ServerConnection getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServerConnection();
        }

        return INSTANCE;
    }

    public static boolean getConnectionState() {
        return connectionState;
    }

    public static void setConnectionState(boolean state) {
        connectionState = state;
    }

    public Socket getSocket() {
        return serverSocket;
    }

    public void setSocket(String serverIP) {
        try {
            this.serverSocket = new Socket(serverIP, 5005);
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUpConnection(String serverIP) {

        try {
            this.serverSocket = new Socket(serverIP, 5005);
            this.outPutStream = serverSocket.getOutputStream();
            this.printStream = new PrintStream(outPutStream, true);
            connectionState = true;
        } catch (IOException ex) {
            failedToConnect();
            closeConnection();
        }
    }

    public void failedToConnect() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Couldn't Connect to Server.");
                alert.showAndWait();
            }
        });
    }

    public void closeConnection() {
        try {
            if (this.printStream != null) {
                this.printStream.close();
            }
            if (this.outPutStream != null) {
                this.outPutStream.close();
            }
            if (this.serverSocket != null) {
                this.serverSocket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public OutputStream getOutPutStream() {
        return outPutStream;
    }

    public void setOutPutStream(OutputStream outPutStream) {
        try {
            this.outPutStream = serverSocket.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PrintStream getPrintStream() {
        return printStream;
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = new PrintStream(outPutStream, true);
    }

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(DataInputStream inputStream) {
        try {
            this.inputStream = new DataInputStream(serverSocket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}

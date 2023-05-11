/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import screens.LoginScreen;
import screens.PlayOnlineScreen;
import screens.PlayerVSPlayerBoardScreen;
import screens.StartScreenBase;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public final class ServerConnection {

    private static ServerConnection INSTANCE;
    private static boolean connectionState = false;
    private Socket serverSocket;
    private OutputStream outPutStream;
    private PrintStream printStream;
    private BufferedReader bufferedReader;
    private DataInputStream inputStream;
    private List<PlayerData> allPlayers;

    public List<PlayerData> getAllPlayers() {
        return allPlayers;
    }

    public void setAllPlayers(List<PlayerData> allPlayers) {
        this.allPlayers = allPlayers;
    }

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

    // TODO : FIX THE UNEXPECTED DISCONNECT EXCEPTION
    public void setUpConnection(String serverIP) {

        try {
            if (serverIP != null) {
                if (!serverIP.isEmpty()) {
                    this.serverSocket = new Socket(serverIP, 5005);
                    this.outPutStream = serverSocket.getOutputStream();
                    this.inputStream = new DataInputStream(serverSocket.getInputStream());
                    this.printStream = new PrintStream(outPutStream, true);
                    this.bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));
                    connectionState = true;
                    requestHandler();
                }
            }
        } catch (SocketException ex) {
            failedToConnect("Couldn't Connect to Server.");
            closeConnection();
        } catch (UnknownHostException ex) {
            failedToConnect("Couldn't Connect to Server.");
            closeConnection();
        } catch (IOException ex) {
            failedToConnect("Couldn't Connect to Server.");
            closeConnection();
        }
    }

    public void failedToConnect(String errMsg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(errMsg);
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
            if (this.inputStream != null) {
                this.outPutStream.close();
            }
            if (this.bufferedReader != null) {
                this.bufferedReader.close();
            }
            if (this.serverSocket != null) {
                this.serverSocket.close();
            }
            connectionState = false;
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

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public void requestHandler() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (connectionState) {
                    try {
                        String jsonString = bufferedReader.readLine();
                        if (jsonString != null) {
                            Gson gson = new Gson();
                            JsonElement jsonElement = gson.fromJson(jsonString, JsonElement.class);
                            System.out.println("JsonElement: " + jsonElement);
                            JsonObject jsonObject = jsonElement.getAsJsonObject();
                            System.out.println("JsonObject: " + jsonObject);
                            handleRequest(jsonObject);
                        }
                    } catch (IOException ex) {
                        closeConnection();
                        StartScreenBase loginScreen = new StartScreenBase(TicTacToe.getCurrentStage());
                        Navigation.getInstance().navigate(loginScreen, TicTacToe.getCurrentStage());
                        failedToConnect("Connection Lost");
                    } catch (SQLException ex) {
                        closeConnection();
                    }
                }
            }
        }).start();

    }

    private void handleRequest(JsonObject jsonObject) throws SQLException {

        JsonObject requestObject = jsonObject.get("request").getAsJsonObject();
        String request = requestObject.get("request").getAsString();

        switch (request) {
            case "SIGNIN": {
                signInRequest(jsonObject);
            }
            break;
            case "SIGNUP": {
                signUpRequest(jsonObject);
            }
            break;
            case "INCOMING MOVE": {
            }
            break;
            case "PLAYER LEFT": {
            }

            break;
            case "ONLINEPLAYERLIST": {
                allPlayers = getAllPlayers(jsonObject);

            }
            break;
            case "GAME REQUEST": {
            }
            break;
        }

    }

    private void signInRequest(JsonObject jsonObject) {
        JsonObject responseObject = jsonObject.get("response").getAsJsonObject();
        String response = responseObject.get("response").getAsString();

        if (response.equals("success")) {
            JsonObject jsonData = jsonObject.get("data").getAsJsonObject();
            String displayName = jsonData.get("displayName").getAsString();
            int totalScore = jsonData.get("totalScore").getAsInt();
            String userId = jsonData.get("userId").getAsString();

            Player.getInstance().setDisplayName(displayName);
            Player.getInstance().setTotalScore(totalScore);
            Player.getInstance().setUserName(userId);

            PlayOnlineScreen playOnlineScreen = new PlayOnlineScreen(TicTacToe.getCurrentStage());
            Navigation.getInstance().
                    navigate(playOnlineScreen, TicTacToe.getCurrentStage());

        } else if (response.equals("fail")) {
            failedToConnect("Wrong Login Data");
        }
    }

    private void signUpRequest(JsonObject jsonObject) {
        JsonElement responseElement = jsonObject.get("response");
        JsonObject signUpObject = responseElement.getAsJsonObject();
        String response = signUpObject.get("response").getAsString();

        if (response.equals("success")) {
            JsonObject jsonData = jsonObject.get("data").getAsJsonObject();
            String displayName = jsonData.get("displayName").getAsString();
            int totalScore = jsonData.get("totalScore").getAsInt();
            String userId = jsonData.get("userId").getAsString();

            Player.getInstance().setDisplayName(displayName);
            Player.getInstance().setTotalScore(totalScore);
            Player.getInstance().setUserName(userId);

            PlayOnlineScreen playOnlineScreen = new PlayOnlineScreen(TicTacToe.getCurrentStage());
            Navigation.getInstance().navigate(playOnlineScreen, TicTacToe.getCurrentStage());

        } else if (response.equals("fail")) {
            failedToConnect("Wrong Registration Data");
        }
    }

    private void incomingMoveRequest(JsonObject jsonObject) {
        String response = jsonObject.get("response").getAsString();
        int move = 0;
        PlayerVSPlayerBoardScreen.updateBoard(move);
    }

    private List<PlayerData> getAllPlayers(JsonObject jsonObject) {
        JsonObject json = jsonObject.get("data").getAsJsonObject();
        JsonArray playersArray = json.get("players").getAsJsonArray();

        Gson gson = new Gson();
        Type playerDataType = new TypeToken<List<PlayerData>>() {
        }.getType();
        List<PlayerData> playerDataList = gson.fromJson(playersArray, playerDataType);

        return playerDataList;
    }

    public void parseSignIn(String username, String password) {
        JsonObject jsonObject = new JsonObject();
        JsonObject signinObject = new JsonObject();
        JsonObject requestData = new JsonObject();
        signinObject.addProperty("username", username);
        signinObject.addProperty("password", password);
        requestData.addProperty("request", "SIGNIN");
        jsonObject.add("data", signinObject);
        jsonObject.add("request", requestData);
        String jsonString = jsonObject.toString();

        printStream.println(jsonString);
    }

    public void parseSignUp(String username, String displayname, String password) {

        JsonObject jsonObject = new JsonObject();
        JsonObject signupObject = new JsonObject();
        JsonObject requestData = new JsonObject();
        signupObject.addProperty("username", username);
        signupObject.addProperty("displayname", displayname);
        signupObject.addProperty("password", password);
        requestData.addProperty("request", "SIGNUP");
        jsonObject.add("data", signupObject);
        jsonObject.add("request", requestData);
        String jsonString = jsonObject.toString();

        printStream.println(jsonString);
    }

    public void parseSignOut() {

        JsonObject jsonObject = new JsonObject();
        JsonObject requestData = new JsonObject();
        requestData.addProperty("request", "SIGNOUT");
        jsonObject.add("request", requestData);
        String jsonString = jsonObject.toString();

        printStream.println(jsonString);
    }

    public void parsePlayerList() {
        String request = "ONLINEPLAYERLIST";
        JsonObject jsonObject = new JsonObject();
        JsonObject requestData = new JsonObject();
        requestData.addProperty("request", request);
        jsonObject.add("request", requestData);
        String jsonString = jsonObject.toString();
        printStream.println(jsonString);
    }

}

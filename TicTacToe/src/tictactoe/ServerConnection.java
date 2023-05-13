/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
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
import java.util.Optional;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import screens.OnlinePlayerListScreenBase;

public final class ServerConnection {

    private static ServerConnection INSTANCE;
    private static boolean connectionState = false;
    private Socket serverSocket;
    private OutputStream outPutStream;
    private PrintStream printStream;
    private BufferedReader bufferedReader;
    private DataInputStream inputStream;
    private List<PlayerData> allPlayers;
    private List<Game> allGames;
    private String player1Name;
    private String player2Name;
    private String vsPlayerID;
    private PlayerVSPlayerBoardScreen currentGameScreen;

    public List<Game> getAllGames() {
        return allGames;
    }

    public void setAllGames(List<Game> allGames) {
        this.allGames = allGames;
    }

    public String getVsPlayerID() {
        return vsPlayerID;
    }

    public void setVsPlayerID(String vsPlayerID) {
        this.vsPlayerID = vsPlayerID;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

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

    public void requestAlert(String player1, String player2) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Request");
                dialog.setHeaderText(null);
                dialog.setContentText(player1 + " has invited you");

                ButtonType acceptButton = new ButtonType("Accept", ButtonBar.ButtonData.OK_DONE);
                ButtonType rejectButton = new ButtonType("Reject", ButtonBar.ButtonData.CANCEL_CLOSE);

                dialog.getDialogPane().getButtonTypes().addAll(acceptButton, rejectButton);

                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(TicTacToe.getCurrentStage());

                dialog.getDialogPane().lookupButton(acceptButton).setStyle("-fx-font-size: 12px;");
                dialog.getDialogPane().lookupButton(rejectButton).setStyle("-fx-font-size: 12px;");

                Optional<ButtonType> result = dialog.showAndWait();
                if (result.isPresent() && result.get() == acceptButton) {
                    parseGameAccepted(player1, player2);
                    player1Name = player1;
                    player2Name = Player.getInstance().getDisplayName();
                    PlayerVSPlayerBoardScreen playerVs = new PlayerVSPlayerBoardScreen(TicTacToe.getCurrentStage(), 2);
                    currentGameScreen = playerVs;
                    Navigation.getInstance().navigate(playerVs, TicTacToe.getCurrentStage());
                    OnlinePlayerListScreenBase.isRunning = false;
                } else {
                    parseGameRejected(player1, player2);
                }
            }
        });
    }

    public void acceptAlert(String player1, String player2) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Request");
                dialog.setHeaderText(null);
                dialog.setContentText(player2 + " Accepted your request");

                ButtonType okButton = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);

                dialog.getDialogPane().getButtonTypes().addAll(okButton);

                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(TicTacToe.getCurrentStage());

                dialog.getDialogPane().lookupButton(okButton).setStyle("-fx-font-size: 12px;");

                Optional<ButtonType> result = dialog.showAndWait();
                if (result.isPresent() && result.get() == okButton) {
                    PlayerVSPlayerBoardScreen playerVs = new PlayerVSPlayerBoardScreen(TicTacToe.getCurrentStage(), 1);
                    currentGameScreen = playerVs;
                    Navigation.getInstance().navigate(playerVs, TicTacToe.getCurrentStage());
                    OnlinePlayerListScreenBase.isRunning = false;
                }
            }
        });
    }

    public void rejectAlert(String player1, String player2) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Request");
                dialog.setHeaderText(null);
                dialog.setContentText(player1 + " Rejected your request");

                ButtonType okButton = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);

                dialog.getDialogPane().getButtonTypes().addAll(okButton);

                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(TicTacToe.getCurrentStage());

                dialog.getDialogPane().lookupButton(okButton).setStyle("-fx-font-size: 12px;");

                Optional<ButtonType> result = dialog.showAndWait();
                if (result.isPresent() && result.get() == okButton) {
                }
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
                            JsonObject jsonObject = jsonElement.getAsJsonObject();
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
            case "PLAYERLEFT": {
                playerLeftAlert(jsonObject);
            }
            break;
            case "ONLINEPLAYERLIST": {
                allPlayers = getAllPlayers(jsonObject);
            }
            break;
            case "GAMEREQUEST": {
                gameRequest(jsonObject);
            }
            break;
            case "GAMEACCEPTED": {
                gameAccepted(jsonObject);
            }
            break;
            case "GAMEREJECTED": {
                gameRejected(jsonObject);
            }
            break;
            case "GAMEMOVE": {
                gameMove(jsonObject);
            }
            break;
            case "GAMEHISTORY": {
                allGames = getAllGames(jsonObject);
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

            System.out.println(jsonObject);

            Player.getInstance().setDisplayName(displayName);
            Player.getInstance().setTotalScore(totalScore);
            Player.getInstance().setUserName(userId);

            PlayOnlineScreen playOnlineScreen = new PlayOnlineScreen(TicTacToe.getCurrentStage());
            Navigation.getInstance().
                    navigate(playOnlineScreen, TicTacToe.getCurrentStage());

        } else if (response.equals("fail")) {
            failedToConnect("Wrong Login Data");
        } else if (response.equals("exist")) {
            failedToConnect("User Already logged in");
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
        } else if (response.equals("exist")) {
            failedToConnect("Username already exist");
        }
    }

    private void gameRequest(JsonObject jsonObject) {

        JsonObject jsonData = jsonObject.get("data").getAsJsonObject();
        String player1 = jsonData.get("player1").getAsString();
        String player2 = jsonData.get("player2").getAsString();

        requestAlert(player1, player2);
    }

    private void gameAccepted(JsonObject jsonObject) {

        JsonObject jsonData = jsonObject.get("data").getAsJsonObject();
        String player1 = jsonData.get("player1").getAsString();
        String player2 = jsonData.get("player2").getAsString();

        acceptAlert(player1, player2);
    }

    private void gameRejected(JsonObject jsonObject) {

        JsonObject jsonData = jsonObject.get("data").getAsJsonObject();
        String player1 = jsonData.get("player1").getAsString();
        String player2 = jsonData.get("player2").getAsString();

        rejectAlert(player1, player2);
    }

    private void gameMove(JsonObject jsonObject) {
        JsonObject jsonData = jsonObject.get("data").getAsJsonObject();
        JsonObject jsonIndex = jsonObject.get("index").getAsJsonObject();
        String user = jsonData.get("user").getAsString();
        String vsPlayer = jsonData.get("vsPlayer").getAsString();
        int index = jsonIndex.get("index").getAsInt();

        currentGameScreen.updateBoard(index);
    }

    private List<PlayerData> getAllPlayers(JsonObject jsonObject) {
        JsonObject json = jsonObject.get("data").getAsJsonObject();
        JsonArray playersArray = json.get("players").getAsJsonArray();

        Gson gson = new Gson();
        Type playerDataType = new TypeToken<List<PlayerData>>() {
        }.getType();
        List<PlayerData> playerDataList = gson.fromJson(playersArray, playerDataType);

        System.out.println(jsonObject);

        return playerDataList;
    }

    private List<Game> getAllGames(JsonObject json) {
        JsonObject dataObject = json.getAsJsonObject("data");
        JsonArray gamesArray = dataObject.getAsJsonArray("data");
        Gson gson = new Gson();
        Type gameListType = new TypeToken<List<Game>>() {
        }.getType();
        List<Game> gamesList = gson.fromJson(gamesArray, gameListType);

        return gamesList;
    }

    private void playerLeftAlert(JsonObject jsonObject) {
        JsonObject jsonData = jsonObject.get("data").getAsJsonObject();
        String left = jsonData.get("left").getAsString();

        PlayOnlineScreen playOnline = new PlayOnlineScreen(TicTacToe.getCurrentStage());
        Navigation.getInstance().navigate(playOnline, TicTacToe.getCurrentStage());

        System.out.println(jsonObject + "........ " + Player.getInstance().getDisplayName());

        failedToConnect(left + " Has left the game");
        ServerConnection.getInstance().setVsPlayerID(null);

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

        closeConnection();

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

    public void parseGameRequest(String player1, String player2) {

        JsonObject jsonObject = new JsonObject();
        JsonObject sendGameObject = new JsonObject();
        JsonObject requestData = new JsonObject();
        sendGameObject.addProperty("player1", player1);
        sendGameObject.addProperty("player2", player2);
        requestData.addProperty("request", "SENDGAMEREQUEST");
        jsonObject.add("data", sendGameObject);
        jsonObject.add("request", requestData);
        String jsonString = jsonObject.toString();

        vsPlayerID = player2;

        printStream.println(jsonString);
    }

    public void parseGameAccepted(String player1, String player2) {

        JsonObject jsonObject = new JsonObject();
        JsonObject sendGameObject = new JsonObject();
        JsonObject requestData = new JsonObject();
        sendGameObject.addProperty("player1", player1);
        sendGameObject.addProperty("player2", player2);
        requestData.addProperty("request", "GAMEACCEPTED");
        jsonObject.add("data", sendGameObject);
        jsonObject.add("request", requestData);
        String jsonString = jsonObject.toString();

        vsPlayerID = player1;

        printStream.println(jsonString);
    }

    public void parseGameRejected(String player1, String player2) {

        JsonObject jsonObject = new JsonObject();
        JsonObject sendGameObject = new JsonObject();
        JsonObject requestData = new JsonObject();
        sendGameObject.addProperty("player1", player1);
        sendGameObject.addProperty("player2", player2);
        requestData.addProperty("request", "GAMEREJECTED");
        jsonObject.add("data", sendGameObject);
        jsonObject.add("request", requestData);
        String jsonString = jsonObject.toString();

        printStream.println(jsonString);
    }

    public void parseGameMove(String player1, String player2, int index) {

        JsonObject jsonObject = new JsonObject();
        JsonObject sendGameObject = new JsonObject();
        JsonObject requestData = new JsonObject();
        JsonObject gameIndex = new JsonObject();
        sendGameObject.addProperty("user", player1);
        sendGameObject.addProperty("vsPlayer", player2);
        requestData.addProperty("request", "GAMEMOVE");
        gameIndex.addProperty("index", index);
        jsonObject.add("data", sendGameObject);
        jsonObject.add("request", requestData);
        jsonObject.add("index", gameIndex);
        String jsonString = jsonObject.toString();

        printStream.println(jsonString);
    }

    public void updateScore(String player1, String player2, String moves) {

        JsonObject jsonObject = new JsonObject();
        JsonObject sendGameObject = new JsonObject();
        JsonObject requestData = new JsonObject();
        sendGameObject.addProperty("winner", player1);
        sendGameObject.addProperty("loser", player2);
        sendGameObject.addProperty("moves", moves);
        requestData.addProperty("request", "UPDATESCORE");
        jsonObject.add("data", sendGameObject);
        jsonObject.add("request", requestData);
        String jsonString = jsonObject.toString();
        printStream.println(jsonString);
    }

    public void getGameHistory() {

        JsonObject jsonObject = new JsonObject();
        JsonObject sendGameObject = new JsonObject();
        JsonObject requestData = new JsonObject();
        sendGameObject.addProperty("user", Player.getInstance().getUserName());
        requestData.addProperty("request", "GAMEHISTORY");
        jsonObject.add("data", sendGameObject);
        jsonObject.add("request", requestData);
        String jsonString = jsonObject.toString();
        System.out.println(jsonString);
        printStream.println(jsonString);
    }

    public void playerLeft() {
        JsonObject jsonObject = new JsonObject();
        JsonObject sendGameObject = new JsonObject();
        JsonObject requestData = new JsonObject();
        sendGameObject.addProperty("playing", vsPlayerID);
        sendGameObject.addProperty("left", Player.getInstance().getDisplayName());
        requestData.addProperty("request", "PLAYERLEFT");
        jsonObject.add("data", sendGameObject);
        jsonObject.add("request", requestData);
        String jsonString = jsonObject.toString();
        System.out.println(jsonString);
        printStream.println(jsonString);
    }

}

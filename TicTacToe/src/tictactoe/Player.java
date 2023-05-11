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
public final class Player {

    private static Player INSTANCE;
    private String displayName;
    private String userName;
    private int totalScore;
    private String currentIP;

    private Player() {
    }

    public static Player getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Player();
        }

        return INSTANCE;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getUserName() {
        return this.userName;
    }

    public int getTotalScore() {
        return this.totalScore;
    }

    public String getCurrentIP() {
        return this.currentIP;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void setCurrentIP(String currentIP) {
        this.currentIP = currentIP;
    }

}
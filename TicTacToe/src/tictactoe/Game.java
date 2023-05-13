package tictactoe;

import java.sql.Timestamp;

public class Game {

    private int GAME_ID;
    private String WINNER_ID;
    private String WINNER_NAME;
    private String LOSER_ID;
    private String LOSER_NAME;
    private String GAME_MOVES;
    private boolean IS_DRAW;
    private Timestamp GAME_DATE;

    public Game(String winner_name, String loser_name,
            String game_moves, Timestamp game_date) {
        WINNER_NAME = winner_name;
        LOSER_NAME = loser_name;
        GAME_MOVES = game_moves;
        GAME_DATE = game_date;
    }

    public int getGAME_ID() {
        return GAME_ID;
    }

    public void setGAME_ID(int GAME_ID) {
        this.GAME_ID = GAME_ID;
    }

    public String getWINNER_ID() {
        return WINNER_ID;
    }

    public void setWINNER_ID(String WINNER_ID) {
        this.WINNER_ID = WINNER_ID;
    }

    public String getWINNER_NAME() {
        return WINNER_NAME;
    }

    public void setWINNER_NAME(String WINNER_NAME) {
        this.WINNER_NAME = WINNER_NAME;
    }

    public String getLOSER_ID() {
        return LOSER_ID;
    }

    public void setLOSER_ID(String LOSER_ID) {
        this.LOSER_ID = LOSER_ID;
    }

    public String getLOSER_NAME() {
        return LOSER_NAME;
    }

    public void setLOSER_NAME(String LOSER_NAME) {
        this.LOSER_NAME = LOSER_NAME;
    }

    public String getGAME_MOVES() {
        return GAME_MOVES;
    }

    public void setGAME_MOVES(String GAME_MOVES) {
        this.GAME_MOVES = GAME_MOVES;
    }

    public boolean isIS_DRAW() {
        return IS_DRAW;
    }

    public void setIS_DRAW(boolean IS_DRAW) {
        this.IS_DRAW = IS_DRAW;
    }

    public Timestamp getGAME_DATE() {
        return GAME_DATE;
    }

    public void setGAME_DATE(Timestamp GAME_DATE) {
        this.GAME_DATE = GAME_DATE;
    }
}

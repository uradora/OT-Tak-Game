/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import takapp.Tile;
import takapp.TakApp;
import static takapp.TakApp.WIDTH;
import static takapp.TakApp.HEIGHT;

/**
 * @author meriraja
 * Methods for keeping track of player turn, used pieces, and for checking if a move is valid
 */
public class GameLogic {
    
    private String playerTurn;
    private Tile selectedTile;
    private boolean gameEnded;
    private int whitePieces;
    private int blackPieces;
    
    public GameLogic() {
        this.playerTurn = "white";
        this.whitePieces = 15;
        this.blackPieces = 15;
    }
    
    public boolean hasSelectedTile() {
        return selectedTile != null;
    }
    
    public void setSelectedTile(Tile tile) {
        this.selectedTile = tile;
    }
    
    public Tile getSelectedTile() {
        return this.selectedTile;
    }

    public int getPlayerPieces() {
        if (this.playerTurn.equals("white")) {
            return this.whitePieces;
        } else {
            return this.blackPieces;
        }
    }
    
    /**
     * Check whose turn it is to be the active player
     * @return the active player in string form
     */
    
    public String checkTurn() {
        
        if (this.playerTurn.equals("white")) {
            return "white";
        } else if (this.playerTurn.equals("black")) {
            return "black";
        } else {
            return null;
        }
    
    }
    
    /**
     * After placing a game piece, reduce the active player's pieces by one
     */
    
    //todo: check that the amount does not go under zero
    public void useOnePiece() {
        if (this.playerTurn.equals("white")) {
            this.whitePieces -= 1;
        } else {
            this.blackPieces -= 1;
        }
    }
    
    /**
     * Switch player turn after the active player has completed an action
     */
    public void switchTurns() {
        
        if (this.playerTurn.equals("white")) {
            this.playerTurn = "black";
        } else if (this.playerTurn.equals("black")) {
            this.playerTurn = "white";
        }

    }
    
    /**
     * Check how many game pieces the active player has left to play
     * @return the number of active player's pieces
     */
    public int playerPiecesLeft() {
        if (this.playerTurn.equals("white")) {
            return this.whitePieces;
        } else {
            return this.blackPieces;
        }
    }
    
    /**
     * Check if attempted move is valid
     * @param oldX the X-coordinate for the starting position of the piece being moved
     * @param oldY the Y-coordinate for the starting position of the piece being moved
     * @param newX the X-coordinate where the attempted move is landing
     * @param newY the Y-coordinate where the attempted move is landing
     * @return false if the move is invalid, true if it is valid 
     */

    // fix
    public boolean isValidMove(int oldX, int oldY, int newX, int newY) {
        
        if (newX < 0 || newX > WIDTH || newY < 0 || newY > HEIGHT) {
            return false;
        } else if (TakApp.gameBoard[newX][newY] == null) {
            return false;
        } else if (TakApp.gameBoard[newX][newY].hasPieces()) {
            return false;
        } else {
            return true;
        }
    }
        
    public boolean playerHasPiecesLeft() {
            
        String activePlayer = checkTurn();
        
        if (activePlayer.equals("white")) {
            if (whitePieces > 0) {
                return true;
            } else {
                return false;
            }
        } else if (activePlayer.equals("black")) {
            if (blackPieces > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
        
    }
}


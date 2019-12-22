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
 * Methods for keeping track of player turn, used pieces, and for checking if a move is valid.
 * @author meriraja
 */
public class GameLogic {
    
    private String playerTurn;
    private boolean gameEnded;
    private int whitePieces;
    private int blackPieces;
    
    /**
     * Initialize first player turn and piece numbers for both players.
     */
    public GameLogic() {
        this.playerTurn = "white";
        this.whitePieces = 15;
        this.blackPieces = 15;
    }

    /**
     * Get the active player's game pieces.
     * @return the number of pieces left
     */
    public int getPlayerPieces() {
        if (this.playerTurn.equals("white")) {
            return this.whitePieces;
        } else {
            return this.blackPieces;
        }
    }
    
    /**
     * Check whose turn it is to be the active player.
     * @return the active player in string form
     */
    
    public String checkTurn() {
        
        if (this.playerTurn.equals("white")) {
            return "white";
        } else {
            return "black";
        } 
    }
    
    /**
     * After placing a game piece, reduce the active player's pieces by one.
     */
    
    public void useOnePiece() {
        if (this.playerTurn.equals("white")) {
            if (this.whitePieces >= 1) {
                this.whitePieces -= 1;
            }
        } else {
            if (this.blackPieces >= 1) {
                this.blackPieces -= 1;
            }
        }
    }
    
    /**
     * Switch player turn after the active player has completed an action.
     */
    public void switchTurns() {
        
        if (this.playerTurn.equals("white")) {
            this.playerTurn = "black";
        } else if (this.playerTurn.equals("black")) {
            this.playerTurn = "white";
        }

    }
    
    /**
     * Check how many game pieces the active player has left to play.
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
     * Check if attempted move is valid.
     * @param oldX the X-coordinate for the starting position of the piece being moved
     * @param oldY the Y-coordinate for the starting position of the piece being moved
     * @param newX the X-coordinate where the attempted move is landing
     * @param newY the Y-coordinate where the attempted move is landing
     * @return false if the move is invalid, true if it is valid 
     */

    /**
     * Checks to see if a move is valid.
     * @param oldX the tile from which the move is started, x-coordinate
     * @param oldY the tile from which the move is started, y-coordinate
     * @param newX the new x-coordinate
     * @param newY the new y-coordinate
     * @return false if move is not valid, true if valid
     */
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
        
    /**
     * Check if the active player has pieces left to place.
     * @return true if player has pieces, false otherwise
     */
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


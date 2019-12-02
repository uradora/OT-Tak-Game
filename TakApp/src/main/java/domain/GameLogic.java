/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import takapp.Piece;
import takapp.TakApp;
import static takapp.TakApp.TILE_SIZE;
import static takapp.TakApp.WIDTH;
import static takapp.TakApp.HEIGHT;
import takapp.Tile;

/**
 *
 * @author meriraja
 */
public class GameLogic {
    
    private String playerTurn;
    private boolean gameEnded;
    
    public GameLogic() {
        this.playerTurn = "white";
    }
    
    public String checkTurn() {
        
        if (this.playerTurn.equals("white")) {
            return "white";
        } else if (this.playerTurn.equals("black")) {
            return "black";
        } else {
            return null;
        }
    
    }
    
    public void switchTurns() {
        
        if (this.playerTurn.equals("white")) {
            this.playerTurn = "black";
        } else if (this.playerTurn.equals("black")) {
            this.playerTurn = "white";
        }

    }
    
    public Piece makePiece(String color, int x, int y) {
        Piece piece = new Piece(color, x, y);
        
        return piece;
    }
    
    public void setPiece(Piece piece, int x, int y) {
        piece.relocate(x * TILE_SIZE, y * TILE_SIZE);
        
        TakApp.updateBoard(piece, x, y);
    }
    
    public void removePiece(Piece piece, int x, int y) {
        TakApp.gameBoard[x][y].piece = null;
    }

    public boolean isValidMove(Piece piece, int oldX, int oldY, int newX, int newY) {
        
            Tile destinationTile = TakApp.gameBoard[newX][newY];
            
            if (destinationTile.hasPiece()) {
                return false;
            } else if (newX < 0 || newX > WIDTH || newY < 0 || newY > HEIGHT) {
                return false;
            } else {
                return true;
            }

        }
}


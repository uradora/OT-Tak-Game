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
            this.playerTurn = "black";
            return "white";
        } else if (this.playerTurn.equals("black")) {
            this.playerTurn = "white";
            return "black";
        } else {
            return null;
        }
    
    }
    
    public Piece makePiece(String color, int x, int y) {
        Piece piece = new Piece(color, x, y);
        
        return piece;
    }
    
    public void setPiece(Piece piece, int x, int y) {
        piece.relocate(x * TILE_SIZE, y * TILE_SIZE);
        
        TakApp.updateBoardAfterPlace(piece, x, y);
    }

    //todo: replace with is valid move
    /*
    public void movePiece(Piece piece, double oldX, double oldY, double mouseX, double mouseY) {
        
            int oldBoardX = (int) Math.floor(mouseX / 100.0);
            int oldBoardY = (int) Math.floor(mouseY / 100.0);
            int boardX = (int) Math.floor(mouseX / 100.0);
            int boardY = (int) Math.floor(mouseY / 100.0);

            Tile oldTile = TakApp.gameBoard[oldBoardX][oldBoardY];
            Tile destinationTile = TakApp.gameBoard[boardX][boardY];
            
            if (destinationTile.hasPiece()) {
                setPiece(oldTile, piece, oldBoardX, oldBoardY);
            } else if (boardX < 0 || boardX > WIDTH || boardY < 0 || boardY > HEIGHT) {
                setPiece(oldTile, piece, oldBoardX, oldBoardY);
            } else {
                setPiece(destinationTile, piece, boardX, boardY);
            }

    } 
    */
}


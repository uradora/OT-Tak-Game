/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package takapp;

import domain.GameLogic;
import static takapp.TakApp.TILE_SIZE;

/**
 * Methods for making new pieces, setting pieces on the board and removing them.
 * @author meriraja
 */
public class PieceService {
    
    private GameLogic logic;

    /**
     * Constructor for class.
     * @param logic gamelogic class
     */
    public PieceService(GameLogic logic) {
        this.logic = logic;
    }
    
    /**
    * Make a new Piece-type object.
    * @param logic game logic class
    * @param color the active player's color 
    * @param x coordinate where the piece is to be placed
    * @param y coordinate where the piece is to be place
    * @return Piece-type object
    */
    public Piece makePiece(GameLogic logic, String color, int x, int y) {
            
        if (logic.playerHasPiecesLeft()) {
            Piece piece = new Piece(logic, this, color, x, y);
            logic.useOnePiece();
                
            return piece;
        } else {
            return null;
        }
    }
    
    /**
     * Set a new piece on the graphical game board and call update from the UI.
     * @param piece the Piece-element to be set on the board
     * @param x the coordinate in which to relocate piece
     * @param y the coordinate in which to relocate piece
     */
    public void setPiece(Piece piece, int x, int y) {
        piece.relocate(x * TILE_SIZE, y * TILE_SIZE);
        
        TakApp.updateBoard(piece, x, y);
    }
    
    /**
     * Remove a piece from board array.
     * @param piece the piece to be removed
     * @param x the coordinate in which the piece was
     * @param y the coordinate in which the piece was
     */
    // fix
    public void removePiece(Piece piece, int x, int y) {
        TakApp.gameBoard[x][y].pieces.remove(TakApp.gameBoard[x][y].pieces.size() - 1);
    }

    
}
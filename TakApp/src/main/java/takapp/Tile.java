/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package takapp;

import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import domain.GameLogic;
import domain.PieceService;

/**
 * @author meriraja
 * Instances of Tile-objects that make up the game board
 */
public class Tile extends Rectangle {
    
    //todo: tile will have to be able to hold multiple pieces
    
    public Piece piece;
    public Piece selectedPiece;
    
    /**
     * Checking is the specified tile holds a piece
     * @return false if the piece is not found, true otherwise
     */
    public boolean hasPiece() {
        return piece != null;
    }

    /*
    public boolean hasSelectedPiece() {
        return selectedPiece != null;
    }
    */
    
    public Tile(GameLogic logic, PieceService pieceservice, int x, int y) {

        setWidth(TakApp.TILE_SIZE);
        setHeight(TakApp.TILE_SIZE);
        
        relocate(x * TakApp.TILE_SIZE, y * TakApp.TILE_SIZE);
        Image tilebg = new Image(getClass().getResourceAsStream("/images/tile.jpg"));
        ImagePattern imagePattern = new ImagePattern(tilebg);
        setFill(imagePattern);
        
        if (this.hasPiece() == false) {
        
            setOnMousePressed(e -> {
                String pieceColor = logic.checkTurn();
                Piece piece = pieceservice.makePiece(logic, pieceColor, x, y);
                this.piece = piece;
                pieceservice.setPiece(piece, x, y);
            });
            
        }
    }
}

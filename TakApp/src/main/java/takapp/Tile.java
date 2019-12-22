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
import java.util.ArrayList;

/**
 * Instances of Tile-objects that make up the game board.
 * @author meriraja
 */
public class Tile extends Rectangle {
        
    public int x;
    public int y;
    public int oldX;
    public int oldY;
    public ArrayList<Piece> pieces;
    public Piece selectedPiece;
    
    /**
     * Checking is the specified tile holds a piece.
     * @return false if the piece is not found, true otherwise
     */
    public boolean hasPieces() {
        return !(pieces.isEmpty());
    }

    public int getXcoordinate() {
        return this.x;
    }
    
    public int getYcoordinate() {
        return this.y;
    }
    
    /** Initialize the Tile object with background graphic and coordinates.
     * Also sets methods for moving and placing pieces on tiles.
     * @param logic gamelogic class
     * @param pieceservice pieceservice class
     * @param x the tile's x-coordinate
     * @param y the tile's y-coordinate
     */
    public Tile(GameLogic logic, PieceService pieceservice, int x, int y) {

        this.x = x;
        this.y = y;
        this.pieces = new ArrayList<>();

        setWidth(TakApp.TILE_SIZE);
        setHeight(TakApp.TILE_SIZE);
        
        relocate(x * TakApp.TILE_SIZE, y * TakApp.TILE_SIZE);
        Image tilebg = new Image(getClass().getResourceAsStream("/images/tile.jpg"));
        ImagePattern imagePattern = new ImagePattern(tilebg);
        setFill(imagePattern);
            
        if (this.hasPieces() == false) {
            setOnMousePressed(e -> {
                String pieceColor = logic.checkTurn();
                Piece piece = pieceservice.makePiece(logic, pieceColor, x, y);
                if (piece != null) {
                    pieceservice.setPiece(piece, x, y);
                    this.pieces.add(piece);
                }
            });
        } else if (this.hasPieces() == true) {
                
            setOnMousePressed(e -> {
                this.oldX = this.getXcoordinate();
                this.oldY = this.getYcoordinate();
            });

            setOnMouseDragged(e -> {
                relocate(e.getSceneX(), e.getSceneY());
            });
        
            setOnMouseReleased(e -> {
            
                double mouseX = e.getSceneX();
                double mouseY = e.getSceneY();

                int newX = (int) Math.floor(mouseX / 100.0);
                int newY = (int) Math.floor(mouseY / 100.0);
            
                boolean validMove = logic.isValidMove(oldX, oldY, newX, newY);
            
                if (validMove) {
                    pieceservice.removePiece(this.pieces.get(this.pieces.size() - 1), oldX, oldY);
                    pieceservice.setPiece(this.pieces.get(this.pieces.size() - 1), newX, newY);
                } else {
                    pieceservice.setPiece(this.pieces.get(this.pieces.size() - 1), oldX, oldY);
                }
            });
        }   
    }
}

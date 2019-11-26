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
import static takapp.TakApp.TILE_SIZE;

/**
 *
 * @author meriraja
 */
public class Tile extends Rectangle {
    
    private Piece piece;
    
    private double mouseX, mouseY;
    
    public boolean hasPiece() {
        return piece != null;
    }
    
    public Tile(GameLogic logic, int x, int y) {
        
        setWidth(TakApp.TILE_SIZE);
        setHeight(TakApp.TILE_SIZE);
        
        relocate(x * TakApp.TILE_SIZE, y* TakApp.TILE_SIZE);
        
        Image tilebg = new Image("file:src/main/res/tile.jpg");
        ImagePattern imagePattern = new ImagePattern(tilebg);
        setFill(imagePattern);
        
        setOnMousePressed(e -> {
            String pieceColor = logic.placePiece();
            Piece piece = makePiece(pieceColor, x, y);
            setPiece(piece, x, y);
        });
    }
    
    private Piece makePiece(String color, int x, int y) {
        Piece piece = new Piece(color, x, y);
        
        return piece;
    }
    
    private void setPiece(Piece piece, int x, int y) {
        piece.relocate(x * TILE_SIZE, y * TILE_SIZE);
        TakApp.updateBoard(piece, x, y);
    }
    
}

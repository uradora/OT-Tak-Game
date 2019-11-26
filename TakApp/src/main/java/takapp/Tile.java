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
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
            
            System.out.println(mouseX);
            System.out.println(mouseY);
            
            String pieceColor = logic.placePiece();
            Piece piece = TakApp.makePiece(pieceColor, mouseX, mouseY);
            TakApp.setPiece(piece, mouseX, mouseY);
        });
    }
    
}

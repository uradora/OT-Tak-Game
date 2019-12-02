/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package takapp;

import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static takapp.TakApp.TILE_SIZE;
import static takapp.TakApp.WIDTH;
import static takapp.TakApp.HEIGHT;


/**
 *
 * @author meriraja
 */
public class Piece extends StackPane {
    
    private String color;
    
    private double mouseX, mouseY;
    private int boardX, boardY;
    
    public Piece(String color, double x, double y) {
        this.color = color;
        
        if (this.color.equals("white")) {
            Image white = new Image("file:src/main/res/white.png");
            ImageView whiteView = new ImageView(white);
            getChildren().addAll(whiteView);
        } else if (this.color.equals("black")) {
            Image black = new Image("file:src/main/res/black.png");
            ImageView blackView = new ImageView(black);
            getChildren().addAll(blackView);
        } else {
            getChildren().addAll();
        }
        
        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });
        
        setOnMouseDragged(e -> {
            relocate(e.getSceneX(), e.getSceneY());
        });
        
        //check which tile the piece is in after moving. 
        //why does the tile never return to original tile?
        setOnMouseReleased(e -> {
            double oldX = mouseX;
            double oldY = mouseY;
            System.out.println(mouseX);
            System.out.println(mouseY);
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
            int oldBoardX = (int) Math.floor(mouseX / 100.0);
            int oldBoardY = (int) Math.floor(mouseY / 100.0);
            System.out.println(oldBoardX);
            System.out.println(oldBoardY);
            boardX = (int) Math.floor(mouseX / 100.0);
            boardY = (int) Math.floor(mouseY / 100.0);
            Tile oldTile = TakApp.gameBoard[oldBoardX][oldBoardY];
            Tile destinationTile = TakApp.gameBoard[boardX][boardY];
            if (destinationTile.hasPiece()) {
                oldTile.setPiece(oldTile, this, oldBoardX, oldBoardY);
            } else if (boardX < 0 || boardX > WIDTH || boardY < 0 || boardY > HEIGHT) {
                oldTile.setPiece(oldTile, this, oldBoardX, oldBoardY);
            } else {
                destinationTile.setPiece(destinationTile, this, boardX, boardY);
            }
        });
        
    }
    
}

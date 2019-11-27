/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package takapp;

import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 *
 * @author meriraja
 */
public class Piece extends StackPane {
    
    private String color;
    private boolean hasPiece;
    
    public Piece(String color, double x, double y) {
        this.color = color;
        
        if (this.color.equals("white")) {
            Image white = new Image("file:src/main/res/white.png");
            ImageView whiteView = new ImageView(white);
            whiteView.relocate(x * TakApp.TILE_SIZE, y * TakApp.TILE_SIZE);
            this.hasPiece = true;
            getChildren().addAll(whiteView);
        } else if (this.color.equals("black")) {
            Image black = new Image("file:src/main/res/black.png");
            ImageView blackView = new ImageView(black);
            this.hasPiece = true;
            getChildren().addAll(blackView);
        } else {
            getChildren().addAll();
        }
    }
    
}

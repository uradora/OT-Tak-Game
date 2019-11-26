/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package takapp;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import static takapp.TakApp.TILE_SIZE;

/**
 *
 * @author meriraja
 */
public class Piece extends StackPane {
    
    private String color;
    
    public String getColor() {
        return color;
    }
    
    public Piece(String color, double x, double y) {
        this.color = color;
        
        if (this.color.equals("white")) {
            Image white = new Image("file:src/main/res/white.png");
            ImageView whiteView = new ImageView(white);
            whiteView.relocate(x * TakApp.TILE_SIZE, y * TakApp.TILE_SIZE);
            getChildren().addAll(whiteView);
        } else if (this.color.equals("black")) {
            Image black = new Image("file:src/main/res/black.png");
            ImageView blackView = new ImageView(black);
            getChildren().addAll(blackView);
        } else {
            getChildren().addAll();
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package takapp;

import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import domain.GameLogic;
import static takapp.TakApp.HEIGHT;
import static takapp.TakApp.WIDTH;

/**
 *
 * @author meriraja
 */
public class Piece extends StackPane {
    
    private String color;
    private int x;
    private int y;
    
    private int oldX;
    private int oldY;

    private GameLogic logic = new GameLogic();
    
    public String getColor() {
        return this.color;
    } 
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }

    public Piece(String color, int x, int y) {
        this.color = color;
        setX(x);
        setY(y);
        
        if (this.color.equals("white")) {
            Image white = new Image(getClass().getResourceAsStream("/images/white.png"));
            ImageView whiteView = new ImageView(white);
            getChildren().addAll(whiteView);
        } else if (this.color.equals("black")) {
            Image black = new Image(getClass().getResourceAsStream("/images/black.png"));
            ImageView blackView = new ImageView(black);
            getChildren().addAll(blackView);
        } else {
            getChildren().addAll();
        }
        
       
        setOnMousePressed(e -> {
            oldX = this.x;
            oldY = this.y;
            System.out.println(oldX);
            System.out.println(oldY);
        });
        
        
        setOnMouseDragged(e -> {
            relocate(e.getSceneX(), e.getSceneY());
        });
        
        setOnMouseReleased(e -> {
            
            double mouseX = e.getSceneX();
            double mouseY = e.getSceneY();
            
            System.out.println(Math.floor(mouseX / 100.0));
            System.out.println(Math.floor(mouseY / 100.0));
            
            int newX = (int) Math.floor(mouseX / 100.0);
            int newY = (int) Math.floor(mouseY / 100.0);
            
            boolean validMove = logic.isValidMove(oldX, oldY, newX, newY);
            
            if (validMove) {
                setX(newX);
                setY(newY);
                logic.removePiece(this, oldX, oldY);
                logic.setPiece(this, newX, newY);
            } else {
                logic.setPiece(this, oldX, oldY);
            }
        });
        
    }
    
}

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
 * @author meriraja
 * Instances of Tile-objects that make up the game board
 */
public class Tile extends Rectangle {
        
    public int x;
    public int y;
    public ArrayList<Piece> pieces;
    public Piece selectedPiece;
    
    /**
     * Checking is the specified tile holds a piece
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
        
        //Add more javadocs here and elsewhere
        //checkstyle and stuff
        //too long method?
        //jos ei vaan saa toimimaan, niin siisti koodia lopussa ja poista toimimattomat
        
        setOnMousePressed(e -> {

            if (!(logic.hasSelectedTile())) {

                if (this.hasPieces() == false) {

                    String pieceColor = logic.checkTurn();
                    Piece piece = pieceservice.makePiece(logic, pieceColor, x, y);
                    if (piece != null) {
                        pieceservice.setPiece(piece, x, y);
                    }

                } else if (this.hasPieces() == true) {
                
                    logic.setSelectedTile(this);
                    System.out.println(logic.hasSelectedTile());
                    System.out.println(logic.getSelectedTile().getXcoordinate()+ ", " + logic.getSelectedTile().getYcoordinate());
                    //SOME GUI ACTION HERE, HIGHLIGHTING TILE BORDERS
                }
            } else if (logic.hasSelectedTile()) {

                int stackLength = this.pieces.size();
                if (this.pieces.get(stackLength - 1).getColor().equals(logic.checkTurn())) {
                    if ((Math.abs(this.getXcoordinate()) - Math.abs(logic.getSelectedTile().getXcoordinate()) <= 1) && (Math.abs(this.getYcoordinate()) - Math.abs(logic.getSelectedTile().getYcoordinate()) <= 1)) {
                        System.out.println("clicked tile x: " + this.getXcoordinate());
                        System.out.println("clicked tile y : " + (this.getYcoordinate()));
                        System.out.println("selected tile x: " + (logic.getSelectedTile().getXcoordinate()));
                        System.out.println("selected tile y: " + (logic.getSelectedTile().getYcoordinate()));
                        //TILES ARE ADJACENT, STACK MOVING ACTION HERE
            
                        //CALCULATE DIRECTION
                        //WHILE STACK LENGTH >= ..., SETPIECE TO THESE COORDINATES; THEN MOVE COORDINATES

                        //MOVE STACK
                        logic.setSelectedTile(null);
                    } else {
                        //ERROR, DO NOTHING
                        return;
                    }
                } else {
                    //ERROR, DO NOTHING
                    return;
                }
            }
        });
    }
}

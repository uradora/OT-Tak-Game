package takapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.Group;
import domain.GameLogic;

/**
 *
 * @author meriraja
 */
public class TakApp extends Application {
    
    //todo: add options for board size
    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 3;
    public static final int HEIGHT = 3;
    private Tile[][] gameBoard = new Tile[WIDTH][HEIGHT];
    
    public static Group tileGroup = new Group();
    public static Group pieceGroup = new Group();
    
    private GameLogic logic = new GameLogic();
    
    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        root.getChildren().addAll(tileGroup, pieceGroup);
        
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile(logic, x, y);
                gameBoard[x][y] = tile;
                
                tileGroup.getChildren().add(tile);
            }
        }
        
        return root;
    }
    
    //todo: set on mouceclicked: makepiece
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Tak App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static Piece makePiece(String color, double x, double y) {
        Piece piece = new Piece(color, x, y);
        
        return piece;
    }
    
    public static void setPiece(Piece piece, double x, double y) {
        //piece.relocate(x * TILE_SIZE, y * TILE_SIZE);
        pieceGroup.getChildren().add(piece);
        //gameBoard[x][y] = piece;
    }
    public static void main(String[] args) {launch(TakApp.class); }
 
}

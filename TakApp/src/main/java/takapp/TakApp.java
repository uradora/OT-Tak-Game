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
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Tak App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void updateBoard(Piece piece, int x, int y) {
        //gameBoard[x][y] = tile;
        //tileGroup.getChildren().add(tile);
        pieceGroup.getChildren().add(piece);
    }

    public static void main(String[] args) {launch(TakApp.class); }
 
}

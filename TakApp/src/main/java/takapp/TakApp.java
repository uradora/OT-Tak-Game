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
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import domain.GameLogic;
import domain.PieceService;

/**
 * @author meriraja
 * Class creates the game board array and visual UI elements
 */
public class TakApp extends Application {
    
    //todo: add options for board size
    
    /**
     * Fixed tile and game board sizes. 
     * Board array and group elements for JavaFX.
     * Creating objects for handling game logic and piece moving services.
     */
    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 4;
    public static final int HEIGHT = 4;
    public static Tile[][] gameBoard = new Tile[WIDTH][HEIGHT];
    
    private static Group tileGroup = new Group();
    private static Group pieceGroup = new Group();
    
    public static GameLogic logic = new GameLogic();
    public static PieceService pieceservice = new PieceService(logic);
    
    private Parent createContent() {
        Pane root = new Pane();

        root.setPrefSize(WIDTH * TILE_SIZE, (HEIGHT * TILE_SIZE) + 20);
        root.getChildren().addAll(tileGroup, pieceGroup);
        
        Label playerTurn = new Label("Player turn: " + logic.checkTurn() + "  ");
        Label piecesLeft = new Label("Player pieces left: " + logic.playerPiecesLeft());
        root.getChildren().addAll(playerTurn, piecesLeft);
        playerTurn.relocate(0, HEIGHT * TILE_SIZE);
        piecesLeft.relocate(150, HEIGHT * TILE_SIZE);
        
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile(logic, pieceservice, x, y);
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
    
    /**
     * Update game situation on the board array and switch turns
     * @param piece Piece-element to be placed on array
     * @param x coordinate on two-dimensional array
     * @param y coordinate on two-dimensional array
     */
    
    public static void updateBoard(Piece piece, int x, int y) {
        
        gameBoard[x][y].piece = piece; 
        
        if (!pieceGroup.getChildren().contains(piece)) {
            pieceGroup.getChildren().add(piece);
        }
        
        logic.switchTurns();
    }
    
    public static void main(String[] args) { 
        launch(TakApp.class); 
    }
}

package takapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import domain.GameLogic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

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
    
    public static HBox playerInfo = new HBox();
    
    private Scene startscene;
    private Scene scene;
    private static Stage stage;
    
    public Stage getStage() {
        return stage;
    }
    
    //todo: add options for modifying board size
    public Scene startScene() {
        GridPane startpane = layout();
        
        Text title = new Text("New Game");
        startpane.add(title, 0, 0, 2, 1);
        
        Button btn = new Button("Start Game (existing user)");
        startpane.add(btn, 1, 4);
        
        Button btn2 = new Button("Start Game (new user)");
        startpane.add(btn2, 2, 4);
                
        btn.setOnAction(e -> {
            getStage().setScene(createContent());
        });
        
        btn2.setOnAction(e -> {
            getStage().setScene(createContent());
        });
                        
        startscene = new Scene(startpane, 600, 400);
        return startscene;
    }
    
    public GridPane layout() {
        GridPane gridPane = new GridPane();
        gridPane.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(5));
        gridPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        return gridPane;
    }
    
    public Scene createContent() {
        
        BorderPane root = new BorderPane();

        root.setPrefSize(WIDTH * TILE_SIZE, (HEIGHT * TILE_SIZE) + 20);
        root.getChildren().addAll(tileGroup, pieceGroup);
        root.setBottom(playerInfo);
        
        Label playerTurn = new Label("Player turn: " + logic.checkTurn() + "  ");
        Label piecesLeft = new Label("Player pieces left: " + logic.playerPiecesLeft());
        playerInfo.getChildren().addAll(playerTurn, piecesLeft);
        
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile(logic, pieceservice, x, y);
                gameBoard[x][y] = tile;
                
                tileGroup.getChildren().add(tile);
            }
        }
        scene = new Scene(root, WIDTH * TILE_SIZE, (HEIGHT * TILE_SIZE) + 20);
        return scene;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        stage = primaryStage;
        
        getStage().setTitle("Tak App");
        getStage().setScene(startScene());
        getStage().show();
    }
    
    /**
     * Update game situation on the board array and switch turns
     * @param piece Piece-element to be placed on array
     * @param x coordinate on two-dimensional array
     * @param y coordinate on two-dimensional array
     */
    
    public static void updateBoard(Piece piece, int x, int y) {
        
        gameBoard[x][y].pieces.add(piece); 
        
        if (!pieceGroup.getChildren().contains(piece)) {
            pieceGroup.getChildren().add(piece);
        }
        
        logic.switchTurns();
        
        Label newTurn = new Label("Player turn: " + logic.checkTurn() + "  ");
        Label newPiecesLeft = new Label("Player pieces left: " + logic.playerPiecesLeft()); 
        playerInfo.getChildren().remove(0);
        playerInfo.getChildren().remove(0);
        playerInfo.getChildren().addAll(newTurn, newPiecesLeft);
    }
    
    public static void main(String[] args) { 
        launch(TakApp.class); 
    }
}

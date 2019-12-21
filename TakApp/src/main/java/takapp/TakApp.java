package takapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Label;
import domain.GameLogic;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import dao.FileUserDao;
import domain.UserService;
import domain.User;

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
    public static UserService userservice;
    
    public static HBox playerInfo = new HBox();
    
    private Scene startscene;
    private Scene scene;
    private Scene loginScene;
    private Scene newUserScene;
    private static Stage stage;
    
    @Override
    public void init() throws Exception {
        
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        
        String userFile = properties.getProperty("userFile");
        
        FileUserDao userDao = new FileUserDao(userFile);
                
        userservice = new UserService(userDao);
    }
    
    public Stage getStage() {
        return stage;
    }
    
    //todo: add options for modifying board size
    public Scene startScene() {
        GridPane startpane = new GridPane();
        
        startpane.setAlignment(Pos.CENTER);
        startpane.setHgap(10);
        startpane.setVgap(10);

        
        Label title = new Label("New Game");
        HBox titlepane = new HBox();
        titlepane.getChildren().add(title);
        startpane.add(titlepane, 1, 1);
        titlepane.setAlignment(Pos.TOP_CENTER);
        
        Button btn = new Button("Start Game (existing user)");
        startpane.add(btn, 1, 4);
        
        Button btn2 = new Button("Start Game (new user)");
        startpane.add(btn2, 2, 4);
                
        btn.setOnAction(e -> {
            getStage().setScene(loginScene());
        });
        
        btn2.setOnAction(e -> {
            getStage().setScene(newUserScene());
        });
                        
        startscene = new Scene(startpane, WIDTH * TILE_SIZE, (HEIGHT * TILE_SIZE) + 20);
        return startscene;
    }
    
    public Scene loginScene() {
        GridPane loginpane = new GridPane();
        
        loginpane.setAlignment(Pos.CENTER);
        loginpane.setHgap(10);
        loginpane.setVgap(10);

        Text logintitle = new Text("Login");
        loginpane.add(logintitle, 0, 0, 2, 1);
        
        Label userName = new Label("Username:");
        loginpane.add(userName, 0, 1);
        
        TextField userNameField = new TextField();
        loginpane.add(userNameField, 1, 1);
        
        Label password = new Label("Password:");
        loginpane.add(password, 0, 2);
        
        PasswordField passField = new PasswordField();
        passField.setPromptText("Password");
        loginpane.add(passField, 1, 2);
        
        Button btn = new Button("Login");
        loginpane.add(btn, 1, 4);
        
        Button backButton = new Button("Return to main menu");
        loginpane.add(backButton, 0, 4);
        
        Label addUserMessage = new Label();
        loginpane.add(addUserMessage, 1, 6);
        
        backButton.setOnAction(e -> {
            getStage().setScene(startscene);
        });
        
        Label loginMessage = new Label();
        loginpane.add(loginMessage, 0, 6, 2, 2);
        
        btn.setOnAction(e -> {
            if (userservice.passwordCorrect(userNameField.getText(), passField.getText())) {
                getStage().setScene(createContent());
            } else {
                loginMessage.setText("Invalid username or password");
                loginMessage.setTextFill(Color.RED);
                userNameField.clear();
                passField.clear();
            }
        });

        loginScene = new Scene(loginpane, WIDTH * TILE_SIZE, (HEIGHT * TILE_SIZE) + 20);
        return loginScene;
    }
    
    public Scene newUserScene() {
        GridPane newUserPane = new GridPane();
        
        newUserPane.setAlignment(Pos.CENTER);
        newUserPane.setHgap(10);
        newUserPane.setVgap(10);
        
        Text newUserTitle = new Text("Add new user");
        newUserPane.add(newUserTitle, 0, 0, 2, 1);
        
        Label userName = new Label("Username:");
        newUserPane.add(userName, 0, 1);
        
        TextField newUserName = new TextField();
        newUserPane.add(newUserName, 1, 1);
        
        Label passWord = new Label("Password:");
        newUserPane.add(passWord, 0, 2);
        
        PasswordField passWordField = new PasswordField();
        passWordField.setPromptText("Password:");
        newUserPane.add(passWordField, 1, 2);
        
        Button addUser = new Button("Create user");
        newUserPane.add(addUser, 1, 4);
        
        Button backButton = new Button("Return to main menu");
        newUserPane.add(backButton, 0, 4);
        
        Label addUserMessage = new Label();
        newUserPane.add(addUserMessage, 1, 6);
        
        backButton.setOnAction(e -> {
            getStage().setScene(startscene);
        });
        
        addUser.setOnAction(e -> {
            if (newUserName.getText().length() < 4) {
                addUserMessage.setText("Username too short");
                addUserMessage.setTextFill(Color.RED);
                passWordField.clear();
            } else if (passWordField.getText().length() < 4) {
                addUserMessage.setText("Password too short");
                addUserMessage.setTextFill(Color.RED);
                passWordField.clear();
            } else if (userservice.createUser(newUserName.getText(), passWordField.getText()) == false) {
                addUserMessage.setText("Username is already in use");
                addUserMessage.setTextFill(Color.RED);
                passWordField.clear();
            } else if (userservice.createUser(newUserName.getText(), passWordField.getText()) == true) {
                addUserMessage.setText("New user added");
                addUserMessage.setTextFill(Color.GREEN);
                getStage().setScene(loginScene());
            }
        });
        
        newUserScene = new Scene(newUserPane, WIDTH * TILE_SIZE, (HEIGHT * TILE_SIZE) + 20);
        return newUserScene;
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

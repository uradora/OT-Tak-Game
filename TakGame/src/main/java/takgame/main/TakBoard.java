/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package takgame.main;

/**
 *
 * @author meriraja
 */

import domain.GameLogic;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class TakBoard extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        //initialize gamelogic class
        GameLogic logic = new GameLogic();

        //create imageviews to represent single pieces
        Image white = new Image("file:src/main/resources/white.png");
        ImageView whiteView = new ImageView(white);
        Image black = new Image("file:src/main/resources/black.png");
        ImageView blackView = new ImageView(black);

        //create UI view elements

        Label title = new Label("Tak");
        Font titleFont = Font.font(40);
        title.setFont(titleFont);

        BorderPane mainPane = new BorderPane();
        mainPane.setTop(title);                
        mainPane.setAlignment(title, Pos.CENTER);

        BorderPane boardPane = new BorderPane();

        GridPane gameBoard = new GridPane();
        gameBoard.setAlignment(Pos.CENTER);
        gameBoard.setHgap(10);
        gameBoard.setVgap(10);
        gameBoard.setPadding(new Insets(10, 10, 10, 10));

        //initialize buttons for the game board
        //onclick event sets button image to white piece
        //todo: make it so that more than one button can have the image at the same time
        //todo: next, compute player turn and change piece color accordingly
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Button button = new Button();
                button.setPrefSize(100, 60);

                gameBoard.add(button, x, y);

                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                        public void handle(ActionEvent event) {
                            String playerTurn = logic.placePiece();
                            if (playerTurn == "white") {
                                button.setGraphic(whiteView);
                            } else if (playerTurn == "black") {
                                button.setGraphic(blackView);
                            }
                        }
                    });

            }
        }
               
        boardPane.setCenter(gameBoard);
        mainPane.setCenter(boardPane);
        Scene view = new Scene(mainPane, 500, 500);

        stage.setScene(view);
        stage.show();

    }
    
    public static void main(String[] args) {
        launch(TakBoard.class);
    }

}

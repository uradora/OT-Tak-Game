/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import takapp.Piece;
import takapp.TakApp;


/**
 *
 * @author meriraja
 */

public class GameLogicTest {

    private GameLogic gamelogic;

    @Before
    public void setUp() {
        this.gamelogic = new GameLogic();
    }

    @Test 
    public void firstTurnIsWhite() {
        String playerTurn = gamelogic.checkTurn();
        assertEquals(playerTurn, "white");
    }

    @Test
    public void blackTurnIsAfterWhiteTurn() {
        gamelogic.switchTurns();
        String playerTurn = gamelogic.checkTurn();
        assertEquals(playerTurn, "black");
    }
    
    /*
    @Test
    public void aNewlyMadePieceIsWhite() {
        String pieceColor = gamelogic.checkTurn();
        Piece piece = gamelogic.makePiece(pieceColor, 0, 0);
        pieceColor = piece.getColor();  
        assertEquals(pieceColor, "white");
    }
    */
    
    /*
    @Test 
    public void gameTurnSwitchesAfterAPieceIsSet() {
        Piece piece = gamelogic.makePiece(gamelogic.checkTurn(), 0, 0);
        gamelogic.setPiece(piece, 0, 0);
        String playerTurn = gamelogic.checkTurn();
        assertEquals(playerTurn, "black");
    }
    */
    
    /*
    @Test
    public void removePieceMakesThatTileEmpty() {
    }
    */
    
    @Test
    public void movingOutOfBoardBoundsReturnFalse() {
        boolean validMove = gamelogic.isValidMove(0, 0, 5, 6);
        assertFalse(validMove);
    }
    
    @Test
    public void ifATileIsNotInitialisedReturnFalse() {
        boolean validMove = gamelogic.isValidMove(0, 0, 2, 2);
        assertFalse(validMove);
    }
}
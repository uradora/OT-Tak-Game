/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
    public void firstTurnIsWhiteAfterThatIsBlack() {
        String playerTurn = gamelogic.checkTurn();
        assertEquals(playerTurn, "white");
        gamelogic.switchTurns();
        playerTurn = gamelogic.checkTurn();
        assertEquals(playerTurn, "black");
    }

    @Test
    public void blackTurnIsAfterWhiteTurn() {
        gamelogic.switchTurns();
        String playerTurn = gamelogic.checkTurn();
        assertEquals(playerTurn, "black");
    }
    
    @Test
    public void whiteTurnIsAfterBlackTurn() {
        gamelogic.switchTurns();
        gamelogic.switchTurns();
        String playerTurn = gamelogic.checkTurn();
        assertEquals(playerTurn, "white");
    }
    
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
    
    @Test
    public void usingAPieceReducesPlayerPiecesByOne() {
        gamelogic.useOnePiece();
        assertTrue(gamelogic.getPlayerPieces() == 14);
    }
    
    @Test
    public void playerPiecesLeftDisplaysCorrectAmountAtStart() {
        assertTrue(gamelogic.getPlayerPieces() == 15);
    }
    
    @Test
    public void switchingTurnsAndPlacingPiecesTwiceReducesTwoBlackPieces() {
        gamelogic.switchTurns();
        gamelogic.useOnePiece();
        gamelogic.useOnePiece();
        assertTrue(gamelogic.getPlayerPieces() == 13);
    }
    
    @Test
    public void playersHave15PiecesAtStart() {
        assertTrue(gamelogic.playerPiecesLeft() == 15);
        gamelogic.switchTurns();
        assertTrue(gamelogic.playerPiecesLeft() == 15);
    }
    
    @Test
    public void whiteHas14PiecesAfterPlacingAPiece() {
        gamelogic.useOnePiece();
        assertTrue(gamelogic.playerPiecesLeft() == 14);
    }
    
    @Test
    public void bothPlayersHavePiecesLeftAtTheBeginning() {
        assertTrue(gamelogic.playerHasPiecesLeft());
        gamelogic.switchTurns();
        assertTrue(gamelogic.playerHasPiecesLeft());
    }
    
    @Test
    public void zeroPiecesLeftReturnFalsePiecesLeft() {
        while (gamelogic.getPlayerPieces() > 0) {
            gamelogic.useOnePiece();
        }
        gamelogic.switchTurns();
        while (gamelogic.getPlayerPieces() > 0) {
            gamelogic.useOnePiece();
        }
        assertFalse(gamelogic.playerHasPiecesLeft());
    }
}
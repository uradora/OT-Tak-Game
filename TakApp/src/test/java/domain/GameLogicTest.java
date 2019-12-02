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
        gamelogic.checkTurn();
        String playerTurn = gamelogic.checkTurn();
        assertEquals(playerTurn, "black");
    }
    
    @Test
    public void aNewlyMadePieceIsWhite() {
        String pieceColor = gamelogic.checkTurn();
        Piece piece = gamelogic.makePiece(pieceColor, 0, 0);
        pieceColor = piece.getColor();  
        assertEquals(pieceColor, "white");
    }

}
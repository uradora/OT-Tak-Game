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
    public void firstTurnIsWhite() {
        String playerTurn = gamelogic.placePiece();
        assertEquals(playerTurn, "white");
    }
    
    @Test
    public void blackTurnIsAfterWhiteTurn() {
        gamelogic.placePiece();
        String playerTurn = gamelogic.placePiece();
        assertEquals(playerTurn, "black");
    }
    
}
    

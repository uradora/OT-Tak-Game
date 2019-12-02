/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author meriraja
 */
public class GameLogic {
    
    private String playerTurn;
    private boolean gameEnded;
    
    public GameLogic() {
        this.playerTurn = "white";
    }
    
    public String checkTurn() {
        
        if (this.playerTurn.equals("white")) {
            this.playerTurn = "black";
            return "white";
        } else if (this.playerTurn.equals("black")) {
            this.playerTurn = "white";
            return "black";
        } else {
            return null;
        }
    
    }

}


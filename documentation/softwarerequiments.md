# OT Virtual Tak Game

## Software requirements specification

### Description

This software is a virtual version of the game Tak, a board game implemented by Cheapass Games. The game was first introduced in the book series Kingkiller Chronicles by Patrick Rothfuss. The rules of Tak can be found [here](http://cheapass.com/wp-content/uploads/2016/07/Tak-Beta-Rules.pdf). This version of the game will include at least a graphical user interface and simple game logic that can be expanded over time.

### Basic functionality

- GUI:
	- The first version will have a 4x4 game board. (done)
	- The game board will have tiles and game pieces that are movable on the board. (done)
	- The GUI will show a track of how many game pieces the players have left (done) and how many times each player has won.
- Game logic:
	- The game will keep track of turn order (done) and how many game pieces the players have left. It will display this in the GUI. (done)
	- The tiles on the game board can be clicked. If the tile represents an empty space, a game piece can be placed on it. If it has the active player's game piece on top, the pieces can be moved. (done)
	- The first version will only feature normal game pieces, played flat on the board. (done) 
	- After every turn, the game will check to see if any win conditions have occurred. If they have, the game ends.

### Functionality to be added

- Options for larger game boards (5x5, 6x6, 7x7 and 8x8 in size).
- As the first version of the game will only feature normal flatstones, functionality for playing standing stones and capstones will have to be added later.
- Game statistics based on player name. These could include how many times the player has won the game, how many pieces the player has placed on average, and how many moves they play in a single game on average. These could possibly be stored in a database.


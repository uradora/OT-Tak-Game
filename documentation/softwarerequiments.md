# OT Virtual Tak Game

## Software requirements specification

### Description

This software is a virtual version of the game Tak, a board game implemented by Cheapass Games. The game was first introduced in the book series Kingkiller Chronicles by Patrick Rothfuss. The rules of Tak can be found [here](http://cheapass.com/wp-content/uploads/2016/07/Tak-Beta-Rules.pdf). This version of the game will include at least a graphical user interface and simple game logic that can be expanded over time.

### Basic functionality

- GUI:
	- There is a login screen and a screen for adding new users. (done)
	- Users can select from these options for game board size: 4x4, 5x5, 6x6 and 7x7. (done)
	- The game board will have tiles and game pieces that are placeable on the board. (done)
	- The GUI will show a track of how many game pieces the players have left and whose turn it is to play (done).
- Game logic:
	- The game will keep track of turn order (done) and how many game pieces the players have left. It will display this in the GUI. (done)
	- The tiles on the game board can be clicked. If the tile represents an empty space, a game piece can be placed on it.  (done)
	- The first version will only feature normal game pieces, played flat on the board. (done) 
- Data persistence:
	- New users can be added and users can log in. This is accomplished by writing usernames and passwords into a separate file. (done)

### Functionality to be added

- Game end conditions are still yet to be added.
- As the first version of the game will only feature normal flatstones, functionality for playing standing stones and capstones will have to be added later.


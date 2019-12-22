# Architecture description

## Package outline 

The package structure of the app is outlined below.

![Package structure](https://github.com/uradora/OT-Tak-Game/blob/master/documentation/packagestructure.jpg)

The basic class diagram for the app is as follows.

![Class diagram](https://github.com/uradora/OT-Tak-Game/blob/master/documentation/classdiagram.jpg)

## UI parts and features

The main UI class, "TakApp", has a single Scene object that contains (for the first iteration) a 4x4 -sized game board that is filled with Tile objects. The Tile objects can hold Piece objects after these have been created. Players can create Piece objects by clicking on Tiles. Tile objects call on PieceService-class that makes the Pieces and sets them on the game board and removes them if necessary (for example, when a Piece is moved). All of these classes require JavaFX elements and graphics and thus they are considered to be part of the UI.

## Game logic

The GameLogic class keeps track of player turn (black or white) and how many game pieces each of the players have left to place on the board. Each player starts the game with 15 pieces. The class also checks if a move is valid whenever a piece is moved, and prevents a move if it is not valid.

Below is a sequence diagram of how the Game Logic class checks if a move is valid.

![Sequence](https://github.com/uradora/OT-Tak-Game/blob/master/documentation/Screenshot_2019-12-03%20Untitled.png)

## Permanent storage of data

This feature doesn't yet exist as of right now.

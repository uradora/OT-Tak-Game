# Architecture description

## Package outline 

The package structure of the app is outlined below.

![Package structure](https://github.com/uradora/OT-Tak-Game/blob/master/documentation/packagestructure.jpg)

The basic class diagram for the app is as follows.

![Class diagram](https://github.com/uradora/OT-Tak-Game/blob/master/documentation/classdiagram.jpg)

## UI parts and features

The package TakApp contains the main UI class, "TakApp". This class handles the separate Scene objects for logging in, creating new users, and selecting game board size, as well as containing the actual gameboard itself. Other UI classes are Tile and Piece elements, as well as the PieceService class that is responsible for setting and making game pieces. All of these classes require JavaFX elements and graphics and thus they are considered to be part of the UI.

## Game logic

The GameLogic class keeps track of player turn (black or white) and how many game pieces each of the players have left to place on the board. Each player starts the game with 15 pieces. The class also checks if a move is valid whenever a piece is moved, and prevents a move if it is not valid.

Below is a sequence diagram of how the Game Logic class checks if a move is valid.

![Sequence](https://github.com/uradora/OT-Tak-Game/blob/master/documentation/Screenshot_2019-12-03%20Untitled.png)

## Permanent storage of data

The class FileUserDao implements the interface UserDao. It is responsible for handling user data (namely, usernames and passwords). The names and passwords are passed to the class via the UserService class that resides in the domain package. The User -class stores names and passwords as its attributes and also resides in the domain package.

# User manual

## Acquiring and running the program

The releases can be found at the ![Releases page](https://github.com/uradora/OT-Tak-Game/releases)

In the terminal, the program can be run by using the following command:

```
java -jar <name of file>.jar
```

## Main menu

Here the user can log in to play the game as an existing user or add a new user. All user names and passwords are stored into a file. If the player chooses to play as an existing user, they will click the button "Start game (existing user) and be taken to a login prompt screen where they will have to provide a correct name and password to start playing the game. New users can be added from a separate screen which will open by clicking the "Start game (New user)" button.

From the main menu the player can also select which size the game board will be rendered as. The options are 4x4, 5x5, 6x6 and 7x7 tiles.

## Data storage

User names and passwords are stored in /users.txt. Each time a new user is added, the FileUserDao class will update the text file. All usernames have to be unique and both usernames and passwords have to be at least 4 characters in length.

## Playing the game

The game is started by clicking on an empty tile. A game piece will appear in the tile. The piece color corresponds to the active player's color. White will go first as the active player, and turn order is switched each time a piece is moved or placed. Each player has 15 pieces available to place and the GUI keeps track of turn order and how many pieces the players have left.

The goal of the game is to make one complete 'road' from one end of the game board to the next.

Functionalities for moving multiple game pieces and counting game wind conditions will be added soon.

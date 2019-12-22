# Testing document

This software has been tested with both unit tests, integration tests and manual testing.

## Unit and integration tests

# UI

UI classes have been left out of unit tests, but these too have been tested manually. Unfortunately, many other classes than the main UI class TakApp (namely, the classes Tile, Piece, PieceService, and to a lesser extent GameLogic) contain graphical game elements that cannot be tested with normal JUnit tests because of the JavaFX elements they contain. 

# Domain

GameLogic -class has been unit tested for those parts that do not contain JavaFX elements. The class User has been tested for overridden equals methods. For integration tests, the mock class FakeUserDao that implements the interface UserDao has been used.

# Dao

The class UserDao has been tested using JUnit’s TemporaryFolder -rules.

# Test coverage



## System testing

Manual testing has been used. All the functionalities that have been specified in the software requirements have been tested with different inputs. 

## Weaknesses

The program does not correctly inform the user that a new user has been succesfully added to the file, even though manual testing reveals this is the case.

In addition, test coverage is weak partly because of the JavaFX elements that some classes still contain.

# Tak Game

This software is a virtual version of the game Tak, a board game implemented by Cheapass Games. The game was first introduced in the book series Kingkiller Chronicles by Patrick Rothfuss. The rules of Tak can be found [here](http://cheapass.com/wp-content/uploads/2016/07/Tak-Beta-Rules.pdf). This version of the game will include at least a graphical user interface and simple game logic that can be expanded over time.

## Documentation

[Software requirements specification](https://github.com/uradora/OT-Tak-Game/blob/master/documentation/softwarerequiments.md)  
[Timesheet](https://github.com/uradora/OT-Tak-Game/blob/master/documentation/timesheet.md)  
[Architecture](https://github.com/uradora/OT-Tak-Game/blob/master/documentation/arkkitehtuuri.md)   
[Architecture description](https://github.com/uradora/OT-Tak-Game/blob/master/documentation/architecturedescription.md)  
[User manual](https://github.com/uradora/OT-Tak-Game/blob/master/documentation/manual.md)  
[Testing document](https://github.com/uradora/OT-Tak-Game/blob/master/documentation/testingdocument.md)

## Releases

[Release week 5](https://github.com/uradora/OT-Tak-Game/releases/tag/viikko5)

## How to use

### Running the program

In the terminal, open the directory which the TakApp jar file is saved in. Give the following command:

```console
java -jar TakApp-1.0.jar 
```

Or, clone the project directory from GitHub and give the following command in the project root:

```console
mvn compile exec:java -Dexec.mainClass=takapp.Main
```
  
### Testing

You can test the program by giving the following command in the project root:

```console
mvn test
```
  
Test coverage report can be generated with the command:

```console
mvn jacoco:report
```
  
You can find the report in the directory *target/site/jacoco/index.html*.

### Checkstyle

You can generate a checkstyle report by giving the following command in the project root:

```console
mvn jxr:jxr checkstyle:checkstyle
```

Checkstyle errors can now be found in the directory *target/site/checkstyle.html*.

### Creating a jar file

You can create a jar file from the project by giving the following command in the project root:

``` console
mvn package
```

You can now find a runnable jar file in the directory *target*. The jar is named *TakApp-1.0-SNAPSHOT.jar*.




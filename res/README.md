# Project 5 - Graphical Adventure Game

## About/Overview

This project is based on Project 3 and 4, and added graphical user interface.

Player can now perform actions with keyboard and mouse clicks on GUI.

## List of features

The game supports:

- A start menu
- A setting menu, where you can custom the game parameters
- A cheat option, set to YES to view full map
- Playing the game, use W/S/A/D to move around, or click the virtual arrow keys.
- Some other keys for game functionalities (eg. shoot)

## How To Run

Install **JRE 11**, which is required by the course, open command line window at jar file's
directory, run jar file with

```java -jar Project05.jar```

## How to Use the Program

Click the button on the screens. Follow the instructions in the game.

## Description of Examples

4 example runs showing 4 different screen of the game.

## Design Changes

- Added **visibility** to locations, unvisited locations will not be shown on screen.
- Added **reset** feature to the model, so player can restart the game with the same map.
- Added another **shoot** method without distance, according to the project description.
- Added extra methods to controllers to interact with GUI.

## Assumptions

- The arrow in the game doesn't need distance. Because the project description doesn't require distance input. Also it makes more sense in a 2D Top-down view game.
- Always center player at the middle, so we don't need to scroll the map if it's out of window space.

## Limitations

- Because of how Java swing handles UI components, it's difficult to make the window zoom in/out proportionally.
- Due to the time limit, there're some potential performance optimization haven't been done.

## Citations

The design pdfs are created with Draw.io

Assets:

- Arrow: https://arydian.itch.io/simple-wooden-bow-and-arrows
- Chest: https://admurin.itch.io/free-chest-animations
- Player: https://camkoalatixd.itch.io/2d-knight-platformer
- Gems: https://opengameart.org/content/gem-icons 
- Font: https://freedesignresources.net/free-pixeboy-pixel-font
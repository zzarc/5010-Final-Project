package dungeon;

import dungeon.entity.CollectableEntity;
import dungeon.entity.Player;
import dungeon.map.DungeonMap;
import java.util.List;

/**
 * The Dungeon interface. Dungeon supports randomly adding treasures to cave, getting player's -
 * description, getting the location's description that player current at, letting player pick up -
 * treasure, and move player with given direction.
 */
public interface Dungeon {

  void generateMap(int height, int width, int interconnectivity, boolean wrapping,
      int treasureRatio, int monsterNumber, boolean cheat);

  /**
   * Get the player's description including location and collected treasures.
   *
   * @return player's description
   */
  String getPlayerDescription();

  /**
   * Get the location's information including coordinates and available treasures.
   *
   * @return location's description
   */
  String getPlayerLocationDescription();

  /**
   * Make player pick up items at current location.
   *
   * @return true if successfully picked up treasures else false
   */
  List<CollectableEntity> pick();

  /**
   * Move player towards the given direction.
   *
   * @param direction the given direction to walk to.
   */
  String move(Direction direction);

  /**
   * Let player shoot arrow with given direction and distance.
   *
   * @param direction the direction to shoot the arrow
   * @param distance  the number of caves the arrow will travel through(including last cave)
   * @return the result
   */
  String shoot(Direction direction, int distance);


  /**
   * Let player shoot arrow with given direction.
   *
   * @param direction the direction to shoot the arrow
   * @return the result
   */
  String shoot(Direction direction);

  /**
   * Get the Dungeon map.
   *
   * @return the dungeon map
   */
  DungeonMap getMap();

  /**
   * Check if game is ended or not (if player moves to the end cell or eaten by monster).
   */
  boolean isGameEnd();

  /**
   * Force the game to end.
   */
  void quitGame();


  /**
   * Get the player.
   *
   * @return the player.
   */
  Player getPlayer();

  /**
   * Reset map.
   */
  void reset();
}

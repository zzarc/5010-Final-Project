package dungeon.entity;

import dungeon.Direction;

import java.util.Map;

/**
 * The player interface. Player represents the object that take action with commands. Player can
 * move up, down, left, right, and pick up items. Player can also shoot arrow to kill monsters in
 * the dungeon, if moving to the end or eaten by monster, the game will end. Player can use arrow to
 * shoot and kill monsters, but will consume the arrow. After using up all arrows, player need to
 * find more arrows in the dungeon.
 */
public interface Player<T extends CollectableEntity> extends DungeonEntity, Cloneable {

  /**
   * Add an item to the player's collected item list.
   *
   * @param item the item to be added
   */
  void addItem(T item);

  /**
   * Move the player towards the given direction.
   *
   * @param d the direction.
   */
  void move(Direction d);

  /**
   * Get the number of arrows player current has.
   *
   * @return the number of arrows.
   */
  int getArrows();

  /**
   * Shoot the arrow towards a direction with distance (how many caves passed, include the last
   * one).
   *
   * @param d        the direction to shoot arrow
   * @param distance the distance to shoot arrow(how many caves passed, including last one)
   * @return the destination location that the arrow ends up with
   */
  Location shoot(Direction d, int distance);

  /**
   * Shoot the arrow towards a direction with distance (how many caves passed, include the last
   * one).
   *
   * @param d the direction to shoot arrow
   * @return the destination location that the arrow ends up with
   */
  Location shoot(Direction d);

  /**
   * Get the list of items that player has collected.
   *
   * @return Player's collected items list
   */
  Map<Class<? extends CollectableEntity>, Integer> getAllItems();
}

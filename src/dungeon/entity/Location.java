package dungeon.entity;

import dungeon.Direction;
import java.util.List;

/**
 * The location interface. Location is the basic unit in the dungeon that the map is made of.
 * Treasures, monsters and arrows will be spawned in locations.
 */
public interface Location {

  /**
   * Get the location x coordinate.
   *
   * @return the x coordinate
   */
  int getX();

  /**
   * Get the location y coordinate.
   *
   * @return the y coordinate
   */
  int getY();

  /**
   * Set the location x coordinate.
   *
   * @param x the x coordinate
   */
  void setX(int x);

  /**
   * Set the location y coordinate.
   *
   * @param y the y coordinate
   */
  void setY(int y);

  /**
   * Get the connected direction of the location.
   *
   * @return all connected directions.
   */
  List<Direction> getConnectedDirections();

  /**
   * Get the location from the connected direction.
   *
   * @param d the connected direction.
   * @return the location from that connected direction.
   */
  Location getLocationFrom(Direction d);

  /**
   * Add the location to the connected direction.
   *
   * @param d the connected direction.
   * @param l the location to be connected.
   */
  void addLocationFrom(Direction d, Location l);

  /**
   * Get the monster from location.
   *
   * @return the monster.
   */
  Monster getMonster();

  /**
   * Add a monster to location.
   *
   * @param m the monster to be added.
   */
  void addMonster(Monster m);

  /**
   * Clear the monster in the location.
   */
  void clearMonster();

  /**
   * Add an item to the location.
   *
   * @param item the item to be added.
   */
  void addItem(CollectableEntity item);

  /**
   * Get all items from the location.
   *
   * @return All items in the location.
   */
  List<CollectableEntity> getItems();

  /**
   * Clear all items in the location.
   */
  void clearItem();

  /**
   * Get the location's type, tunnel or cave.
   *
   * @return location's type.
   */
  LocationType getType();

  /**
   * Get the smell level at the location.
   *
   * @return the smell level.
   */
  SmellLevel getSmellLevel();

  /**
   * Add smell to the location.
   *
   * @param level how many levels to add.
   */
  void addSmell(int level);

  /**
   * Remove smell from the location.
   *
   * @param level how many levels to remove.
   */
  void removeSmell(int level);

  /**
   * remove the smell from location.
   */
  void clearSmell();

  /**
   * Visit the location to gain visibility.
   */
  void visit();

  /**
   * get visited status.
   *
   * @return boolean of visited or not.
   */
  boolean getVisited();

  /**
   * Restore location for replay.
   */
  void restore();

  /**
   * Back up location data.
   */
  void backup();
}

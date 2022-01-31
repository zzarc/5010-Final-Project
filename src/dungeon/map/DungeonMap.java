package dungeon.map;

import dungeon.entity.Location;

/**
 * The Grid interface. Grid is the object that represents the map of the dungeon. Grid consists of
 * locations, in which treasures can be generated at. Grid will be generated with given n, m,
 * interconnectivity, and wrapping. Start and End cell will be generated randomly.
 */
public interface DungeonMap {

  /**
   * Get the location with given x, y coordinate.
   *
   * @param x x coordinate
   * @param y y coordinate
   * @return
   */
  Location getLocationAt(int x, int y);

  /**
   * Get the grid's height.
   *
   * @return grid's height
   */
  int getHeight();

  /**
   * Get the grid's width.
   *
   * @return grid's width
   */
  int getWidth();

  /**
   * Get grid's interconnectivity.
   *
   * @return grid's interconnectivity
   */
  int getInterconnectivity();

  /**
   * Get if grid is wrapped or not.
   *
   * @return true if grid is wrapped else false
   */
  boolean getWrapping();

  /**
   * Get the starting cell.
   *
   * @return the starting cell
   */
  Location getStart();

  /**
   * Get the end cell.
   *
   * @return the end cell
   */
  Location getEnd();

  /**
   * Get all locations.
   *
   * @return the location 2d array.
   */
  Location[][] getLocations();
}

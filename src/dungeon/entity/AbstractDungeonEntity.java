package dungeon.entity;

/**
 * Abstract Entity in the dungeon. It has the location that they are current at.
 */
public class AbstractDungeonEntity implements DungeonEntity {

  protected Location currentLocation;

  /**
   * Get the location entity is currently at.
   *
   * @return the location entity is currently at.
   */
  public Location getCurrentLocation() {
    return this.currentLocation;
  }
}

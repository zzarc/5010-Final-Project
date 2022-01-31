package dungeon.entity;

/**
 * The DungeonEntity interface. Dungeon Entity must have the location they are associated to.
 */
public interface DungeonEntity {

  /**
   * Get the location entity is currently at.
   *
   * @return the location entity is currently at.
   */
  Location getCurrentLocation();
}

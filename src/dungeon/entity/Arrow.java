package dungeon.entity;

/**
 * The Arrow in the dungeon. Arrow will be spawned in the dungeon and can be picked up by players.
 * Player can use arrow to shoot and kill monsters, but will consume the arrow. After using up all
 * arrows, player need to find more arrows in the dungeon.
 */
public class Arrow implements CollectableEntity {

  @Override
  public String toString() {
    return "Arrow";
  }
}
